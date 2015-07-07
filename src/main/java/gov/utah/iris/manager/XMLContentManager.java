package gov.utah.iris.manager;

import gov.utah.iris.bean.StaticConfig;
import gov.utah.iris.bean.Section;
import gov.utah.iris.bean.Page;
import gov.utah.iris.util.XMLUtil;

import java.util.*;

import org.jdom.*;

/**
 * Date: May 18, 2005
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
public class XMLContentManager {

    private String configFilePath;
    private StaticConfig staticConfig;

    /**
     * Private no-arg constuctor - must use constructor with config file path
     */
	private XMLContentManager() {
        // do nothing
    }

    /**
     * Constructor that requires the path to the static-config.xml file. It saves the
     * static-config.xml file path and parses it with buildStaticConfig method
     * @param configFilePath - String path to static-config.xml
     */
    public XMLContentManager( String configFilePath ) {
        this.configFilePath = configFilePath;
        this.staticConfig = new StaticConfig();
        this.buildStaticConfig();
    }

    /**
     * Parses the static-config.xml file and populates the properties with its values
     */
    public void buildStaticConfig() {

        // parse xml config file
        Document doc = XMLUtil.parse( configFilePath, false );

        // get the document root
        Element static_config = doc.getRootElement();

        // Build section beans for each section and add it to config file
        LinkedHashMap sectionsMap = new LinkedHashMap();
        List sectionsList = static_config.getChildren("section");
        Iterator sectionsIt = sectionsList.iterator();
        while ( sectionsIt.hasNext() ) {

            // get the XML element representing the section
            Element sectionElem = (Element)sectionsIt.next();

            // new Section
            Section section = new Section();

            // for each of the pages, create a page and add it to pagesMap
            LinkedHashMap pagesMap = new LinkedHashMap();
            ArrayList menuList = new ArrayList();
            List pagesList = sectionElem.getChildren("page");
            Iterator pagesIt = pagesList.iterator();
            while ( pagesIt.hasNext() ) {

                // get the page element
                Element pageElem = (Element)pagesIt.next();

                // create new Page object to hold the data
                Page page = new Page();

                // populate the page bean
                page.setPageName( pageElem.getAttributeValue("name") );
                page.setDefaulPage( Boolean.valueOf(pageElem.getAttributeValue("default")).booleanValue() );
                page.setTitleKey( pageElem.getChildText("title-key") );
                page.setMenuKey( pageElem.getChildText("menu-key") );
                page.setContentPath( pageElem.getChildText("content-path") );

                // put the page in the pagesMap
                pagesMap.put( page.getPageName(), page );

                // if this page is the default page, mark it in the section
                if ( page.isDefaulPage() ) {
                    section.setDefaultPageName( page.getPageName() );
                }

            }

            // set the element data into the bean
            section.setSectionName( sectionElem.getAttributeValue("name") );
            section.setHref(sectionElem.getAttributeValue("href"));
            section.setDefaultSection( Boolean.valueOf(sectionElem.getAttributeValue("default")).booleanValue() );
            section.setLabelKey( sectionElem.getChildText("label-key") );
            section.setPages( pagesMap );

            // if this is the default section, mark it as defalult in staticConfig
            if ( section.isDefaultSection() ) {
                this.staticConfig.setDefaultSectionName( section.getSectionName() );
            }

            // put the section in the map
            sectionsMap.put( section.getSectionName(), section );

        }

        // save sections in staticConfig
        this.staticConfig.setSections( sectionsMap );

    }

    /**
     * Returns the static config object
     * @return
     */
    public StaticConfig getStaticConfig() {
        return this.staticConfig;
    }

    /**
     * Returns the content path as a String based on the specified section and page
     * @param sectionName - String section
     * @param pageName - String page
     * @return String - the content path
     */
    public String getContentPath( String sectionName, String pageName ) {

        String contentPath = "";

        if ( sectionName != null && pageName != null ) {
            Section sectionObj = staticConfig.getSection(sectionName);
            if ( sectionObj != null ) {
                Page pageObj = sectionObj.getPage(pageName);
                if ( pageObj != null )
                    contentPath = pageObj.getContentPath();
                else
                    System.out.println("pageObj is null");
            }
            else {
                System.out.println("sectionObj is null");
                System.out.println("staticConfig status: " + staticConfig);
            }
        }

        return contentPath;
    }

    /**
     * Returns a Collection of Pages
     * @param section - String section name
     * @return Collection - list of key strings
     */
    public Collection getPages( String section ) {
        LinkedHashMap pages = staticConfig.getSection( section ).getPages();
        return pages.values();
    }

    /**
     * Returns a Collection of Sections
     * @return Collection - section
     */
    public Collection getSections() {
        LinkedHashMap sections = staticConfig.getSections();
        return sections.values();
    }

    /**
     * Returns the name of the default section
     * @return String - section name
     */
    public String getDefaultSectionName() {
        return staticConfig.getDefaultSectionName();
    }

    /**
     * Returns the name of the default page for the specified section
     * @param section - String name of the section
     * @return String - page name
     */
    public String getDefaultPageName( String section ) {
        return staticConfig.getSection( section ).getDefaultPageName();
    }
}
