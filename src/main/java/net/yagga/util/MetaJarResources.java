/*
 * This file is part of MiniInstaller, a self installer builder for Java
 * Copyright (C) 2002  Walter Gamba
 * mailto:walter@yagga.net
 * http://www.yagga.net/java/miniinstaller
 *
 * MiniInstaller is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * MiniInstaller is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * As the time of writing, the GNU General Public Licene can be
 * found at http://www.gnu.org/licenses/gpl.txt
 *
 */

package net.yagga.util;

import java.io.*;
import java.util.*;
import java.util.jar.*;
import java.net.*;

/**
 * Class that implements reading data from a Jar file. The Jar file can be in the class
 * path or included in the jar file we are executing!.
 *
 * This class reads bytes arrays for a given entry in a given jar file. If the case  is the simple
 * one:
 * <pre>
 *
 * ./Test.class
 * ./net/yagga/util/MetaJarResource.class
 * ./sample.jar
 *   +- icon.gif
 * </pre>
 *
 * one can read data from the file "sample.jar" via this class, in the main method of the Test class.
 * This case is more or less the same as seen in JavaWorld's tip 49 (JarResource). The class reads the
 * specified Jar entry and returns the byte array. The trick is a bit more complicated if we are in such a case:
 * <pre>
 *
 * ./all.jar
 *   +- Test.class
 *   +- net/yagga/util/MetaJarResource.class
 *   +- sample.jar
 *     +- icon.gif
 * </pre>
 * If we execute the Jar file "all.jar" Test class cannot find a resource inside the sample.jar file using the
 * usual trick in JArResource. We must extract in a JarResource way from the top level Jar (all.jar) the entry for
 * sample.jar, then extracting from it the entry from icon.gif.
 * I have not found a simple way to do this:<BR>
 * <OL>
 * <LI>I extract the bytes for the sample.jar entry of the top level jar</LI>
 * <LI>I save those bytes in the /tmp dir of the System</LI>
 * <LI>I read from this actual file the sizes of each entry in the sub-jar file (sample.jar)</LI>
 * <LI>I delete the temp file, and then I read from the bytes of the sub jar the given entry, for the number of bytes I have obtained in step 3</LI>
 * </OL>
 * @see <a href="http://www.javaworld.com/javaworld/javatips/jw-javatip49.html"> JavaWorld's tip 49</a>
 *
 * @author Walter Gamba
 */
@SuppressWarnings({"rawtypes", "unchecked", "resource"})
public class MetaJarResources
{
	private Hashtable htSizes=new Hashtable();
	private String jarFile;

	//for self referencing jar
	private boolean executingFromJar=false; //true if this class is executed inside a jar
	private Hashtable metaJarContent=new Hashtable();
	private String jarJarFile;
	private Manifest  metaManifest=null;

	/**
	 * Construct a MetaJarResources file, a jar file that can be in the class path (simple case) or
	 * enclosed in the same jar file that is executing now.
	 *
	 * This constructor decides if the environment in which this class is called is
	 * <OL>
	 * <LI>we are created from a class in the classpath</LI>
	 * <LI>we are created from a class included in a Jar in the classpath</LI>
   	 * </OL>
 	 * and consequently if the jar file passed is in the classspath or enclosed in the jar file we are
 	 * currently executing.<BR>
 	 * Note that the behaviour of the class in either case is transparent regards to the user.
 	 *
 	 * @param jFile the name of the Jar file we want to read resources from.
   	 */
	public MetaJarResources(String jFile)
	{
    	super();
	    this.jarFile=jFile;

    	try{
     		URL u=getClass().getResource("/"+jarFile);
	     	//Ut.infoln("JAR URL of "+jarFile+" is"+u);
    		URLConnection uc=null;
			if(u!=null)
		        uc=u.openConnection();

			if(uc instanceof JarURLConnection)
			{
	    	    executingFromJar=true;
    	    	JarURLConnection juc = (JarURLConnection)uc;
	    	    jarJarFile=jarFile;
    		    //jar file is containing file, not the one specified..
		        //trying to get this name..
        		jarFile=juc.getJarFileURL().getFile();
						//now take care of 1.3/1.4 glitches:
						//in JDK1.3 the URL of getResource did not translate spaces to %20
						//in jdk1.4 it does (correctly) .. so in order
						//to account for file names with spaces..
						jarFile=URLDecoder.decode(jarFile, "UTF-8");
		        //read in sizes for top level jar, anyway

        		initTopJarSizes();
		        //reads jar-in-jar file
		        readJarInJar();
			}
			else //if (uc instanceof FileURLConnection){
			{
				executingFromJar=false;
				initTopJarSizes();
			}
	    }
	    catch(IOException ioe){
	    	Ut.error("Error getting file from file"+jarFile+":"+ioe);
      		jarFile="";
    	}
	}

  	/**
     * Method that actually reads raw bytes.
     *
     * this method reads in the appropriate way raw bytes for the given entry.
     * @param entryName a String containing the full qualified name of the entryi in the jar file
     * the name must be given in the usual directory/filename format, for example<BR>
     * "img/icon.gif"<br>
     * "net/yagga/test/Test.class"<BR>
     *
     * @return the array of bytes read foir the given entry, or null if sonething went wrong
  	 */
	public byte[] getBytes(String entryName)
	{
		byte b1[]=null;
		//Ut.infoln("MJR:getBytes execFormJar="+executingFromJar+", entry="+entryName);
		if(executingFromJar)
			b1=(byte [])metaJarContent.get(entryName);
		else
			b1=readBytesFromTopJar(entryName);
		return b1;
	}

	/**
	 * Return the manifest for the jar file.
	 * This can be the manifest of the plain Jar  or the manifest of the jar file enclosed in the executing jar file.
	 * @return the Manifest of the jar file, or null if there is an error.
	 */
	public Manifest getManifest()
	{
		try{
			if(executingFromJar)
				return metaManifest;
			else
			{
				JarFile jf=new JarFile(jarFile);
				return jf.getManifest();
			}
		}catch(IOException ioe){
			Ut.error("IOE getting main class "+ioe);
			return null;
		}
	}

	/**
	 * Return the actual jar name.
	 * This can be the top level Jar (whose name we usually ignore) or the jar file name
	 * we have passed in the constructor.
	 * @return the file name of the top-level jar file (if searching a Jar from inside an executing jar) or
	 * the jar name as we have passed it to the Constructor.
	 */
	public String getActualJarName(){
    	if(executingFromJar)
	      return jarJarFile;
    	else
	      return jarFile;
	}

	/**
	 * Reads in self contained jar file.
	 *
	 * This is the case if the jar file we want to read is jarred in the jar currently executing
	 * so:
	 * <PRE>
	 * FOO.jar
	 * +- Installer.class
	 * +- net/yagga/util/MetaJarResources.class
	 * +- PACK1.jar
	 *    +- ClassIWantToInvoke.class <===
	 * </PRE>
	 */
	private void readJarInJar()
	{
		try
		{
			//read entry from top-level jar.
			//the bytes read are bytes of a JAR file contained in this top-level jar file
			byte[] jarE=readBytesFromTopJar(jarJarFile);

      //if jarJarFile has a path preceding its name strip it...
      int idx=jarJarFile.lastIndexOf("/"); //UNIX style
      if(idx==-1)
        idx=jarJarFile.lastIndexOf("\\"); //DOS STYLE
      if(idx>=0)
        jarJarFile=jarJarFile.substring(idx+1);

			//save jar contained in this jar in a temp file
			//so save PACK1.jar in a temp file

			//to get temp dir
			String tmpDir=System.getProperty("java.io.tmpdir");

			File jarInJarFile=new File(tmpDir,jarJarFile);
			FileOutputStream fout=new FileOutputStream(jarInJarFile.getCanonicalPath());

			//save bytes in temp jar file
			for(int i=0;i<jarE.length;i++)
				fout.write(jarE[i]);
			fout.close();
			fout=null;

			//reads in temp jar file sizes (in this case, PACK1.jar)
			Hashtable sizes=new Hashtable();
			JarFile jf=new JarFile(jarInJarFile);
			try
			{
				Enumeration e=jf.entries();
				while (e.hasMoreElements())
				{
					JarEntry ze=(JarEntry)e.nextElement();
					//System.out.println("Entry:"+ze+", Size="+ze.getSize()+", CSize="+ze.getCompressedSize());
					sizes.put(ze.getName(),new Integer((int)ze.getSize()));
				}
				//reads in Manifesdt
				metaManifest=jf.getManifest();
				jf.close();
			}
			catch(IOException ioe)
			{
				Ut.error("Error reading sizes in '"+fout+"':"+ioe);
			}

			//once I've read dims i can read data
			//and thus I can safely delete temp jar file
			jarInJarFile.delete();

			//read in data from byte array
			ByteArrayInputStream bais=new ByteArrayInputStream(jarE);
			JarInputStream jis=new JarInputStream(bais);
			JarEntry je=null;
			while ((je=jis.getNextJarEntry())!=null)
			{
				if (je.isDirectory())
					continue;

				int size=(int)je.getSize();
				if(size==-1)
					size=((Integer)sizes.get(je.getName())).intValue();

				byte[] b=new byte[(int)size];
				int rb=0;
				int chunk=0;
				while (((int)size - rb) > 0)
				{
					chunk=jis.read(b,rb,(int)size - rb);
					if (chunk==-1)
						break;
					rb+=chunk;
				}
				//save data in hash map
				metaJarContent.put(je.getName(),b);

				//System.out.println("Meta entry+ URL="+je+", sz="+size);
			}
			jis.close();
		}
		catch(FileNotFoundException fnfe)
		{
			Ut.error(fnfe.toString());
		}
		catch(IOException ioe){
			Ut.error(ioe.toString());
		}
	}

  	/**
     * Read the sizes for top-level JAR file.
     * This can be the sole JAR file, or
     * it can be the top level jar file from which we are executing thic class, and in this
     * case sizes can be used to read further into the JAR, for another JAR file
	 */
	private void initTopJarSizes()
	{
		try
		{
			JarFile zf=new JarFile(jarFile);
			Enumeration e=zf.entries();
			while (e.hasMoreElements())
			{
				JarEntry ze=(JarEntry)e.nextElement();
				htSizes.put(ze.getName(),new Integer((int)ze.getSize()));
			}
			zf.close();
		}
		catch(IOException ioe){
			// suppress the error
			//System.err.println("TOP Error reading sizes in '"+jarFile+"':"+ioe);
		}
	}

	/**
     * Reads bytes from Top level Jar file.
     * Can be used standalone, or to read in another Jar file
     * and then, using readJarInJar, filling the metaJarContent hashtable
     * @param entryName a String containig the top-level entry we eant to read. Ther entry mustbe given in the
     * usual directory/filename format
     * @return an array poff bytes for the entry, or null if there was an erro
  	 */
	private byte[] readBytesFromTopJar(String entryName)
	{
	    try
    	{
			JarInputStream zis=getTopJIS();
			JarEntry ze=null;
			while ((ze=zis.getNextJarEntry())!=null)
			{
				if (ze.isDirectory())
					continue;

				int size=(int)ze.getSize();
				if (size==-1)
					size=((Integer)htSizes.get(ze.getName())).intValue();

				if(ze.getName().equals(entryName))
				{
					//Ut.infoln(">>>>Entry:"+ze.getName()+", Size="+ze.getSize()+", CSize="+ze.getCompressedSize());
					byte[] b=new byte[(int)size];
					int rb=0;
					int chunk=0;
					while (((int)size - rb) > 0)
					{
						chunk=zis.read(b,rb,(int)size - rb);
						if (chunk==-1)
						break;
						rb+=chunk;
					}
					zis.close();
					return b;
				}
			}
			zis.close() ;
		}
		catch (NullPointerException e) {
			Ut.error("'"+jarFile+"':(done)"+e);
		} catch (FileNotFoundException e) {
			Ut.error("'"+jarFile+"':"+e);
		} catch (IOException e) {
			Ut.error("'"+jarFile+"':"+e);
		}
		return null;
	}

	/**
     * Get top level JAR input stream.
     * @return the JarInputStream associated with the top level jar file
	 */
	private JarInputStream getTopJIS()
	{
		//
		try{
			JarFile jf=new JarFile(jarFile);
			if(jf!=null)
				return new JarInputStream(new FileInputStream(jarFile));
		}
		catch(Exception e)
		{
			Ut.error("ERROR:"+e);
		}

		try
		{
			//System.out.println("getTopJIS "+jarFile);
			InputStream is=openResource(jarFile);
			return new JarInputStream(is);
		}
		catch(IOException io){
			Ut.error("IOE:"+io);
		}
		return null;
	}

	/**
	 * open a stream from the environment.
	 * It can be from the file system or from inside the top-level jar
	 * @param filename the filename of the resource we want to read
	 * @return the InputStram associated with the given resource
	 */
	public InputStream openResource(String filename)
	{
		try
		{
			//System.err.println("MJR:openResource:"+filename);
			InputStream is=getClass().getResourceAsStream("/"+filename);
			is.available();//to generate null ptr exceptio
			return is;
		}
		catch (java.io.IOException e)
		{
			Ut.error("RM2: file not found:'"+filename+"'");
			System.exit(1);
		}
		catch(NullPointerException e2){
			Ut.error("RM3: file not found:'"+filename+"'");
			//e2.printStackTrace();
			System.exit(1);
		}
		return null;
	}

}

