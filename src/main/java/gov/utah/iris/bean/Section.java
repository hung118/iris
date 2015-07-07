package gov.utah.iris.bean;

import java.util.*;

/**
 * This class is a bean that discribes a section of static site content.
 * Each section can have multiple pages. It can be the default section.
 * It has a name that should uniquely identify it from other sections.
 *
 * Date: May 20, 2005
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class Section {

    private String sectionName = "";
	private LinkedHashMap pages = new LinkedHashMap();
    private boolean isDefaultSection = false;
    private String defaultPageName = "";
    private String labelKey = "";
    private String href = "";

    /**
     * Returns the default page name
     * @return String - default page name
     */
    public String getDefaultPageName() {
        return defaultPageName;
    }

    /**
     * Sets the default page name to the specified page name
     * @param defaultPageName - String page name
     */
    public void setDefaultPageName(String defaultPageName) {
        this.defaultPageName = defaultPageName;
    }

    /**
     * Returns the name of the section
     * @return String - section name
     */
    public String getSectionName() {
        return sectionName;
    }

    /**
     * Sets the section name to the specified section name
     * @param sectionName - String section name
     */
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    /**
     * Returns a LinkedHashMap of Page objects in this section
     * @return LinkedHashMap - key is the page name and value is a Page object
     */
    public LinkedHashMap getPages() {
        return pages;
    }

    /**
     * Sets the pages related to this section to be the specified LinkedHashMap of pages
     * @param pages - LinkedHashMap of pages. Key is page name and value is a Page object
     */
    public void setPages(LinkedHashMap pages) {
        this.pages = pages;
    }

    /**
     * Returns true if this is the default section
     * @return boolean - True if this is the default section
     */
    public boolean isDefaultSection() {
        return isDefaultSection;
    }

    /**
     * Sets the default section property to the specified boolean value
     * @param defaultSection - boolean
     */
    public void setDefaultSection( boolean defaultSection ) {
        isDefaultSection = defaultSection;
    }

    /**
     * Returns the key for the section label
     * @return String - label key
     */
    public String getLabelKey() {
        return labelKey;
    }

    /**
     * Sets the label key to the specified label key
     * @param labelKey - String label key
     */
    public void setLabelKey(String labelKey) {
        this.labelKey = labelKey;
    }

    /**
     * Returns the specified page
     * @param pageName - the name of the page to get
     * @return Page - the specified page
     */
    public Page getPage( String pageName ) {
        return (Page) pages.get( pageName );
    }

    /**
     * Returns an ArrayList of menu keys (Strings)
     * @return ArrayList - menu key list
     */
    public ArrayList getMenuKeyList() {

        // The ArrayList that gets returned
        ArrayList menuKeyList = new ArrayList();

        // get all the menu keys
        Set pageNames = pages.keySet();
        Iterator pageNamesIt = pageNames.iterator();
        while ( pageNamesIt.hasNext() ) {
            Page page = getPage( (String) pageNamesIt.next() );
            menuKeyList.add( page.getMenuKey() );
        }

        return menuKeyList;
    }

    /**
     * Returns the link of the page
     * @return String - link
     */
    public String getHref() {
        return href;
    }

    /**
     * Set the href to the specified link
     * @param href - String href
     */
    public void setHref(String href) {
        if ( href != null )
            this.href = href;
    }

    /**
     * Returns a string representation of the current state of the section
     * @return String - A string representation of the section
     */
    public String toString() {

        // return the status of the page
        String sectionNameStr = "\nSection Name: " + sectionName;
        String isDefaultSectionStr = "\nIs Default Section: " + isDefaultSection;
        String defaultPageNameStr = "\nDefault Page Name: " + defaultPageName;
        String labelKeyStr = "\nLabelKey: " + labelKey;
        String pagesStr = "\nNumber of pages: " + pages.size();

        return sectionNameStr + isDefaultSectionStr + defaultPageNameStr + labelKeyStr + pagesStr;
    }

    /**
     * Prints to the console the current state of the section
     */
    public void print() {

        // print out the status of the page
        System.out.println("\nSection Name: " + sectionName);
        System.out.println("\nIs Default Section: " + isDefaultSection);
        System.out.println("\nDefault Page Name: " + defaultPageName);
        System.out.println("\nLabelKey: " + labelKey);
        System.out.println("\nNumber of pages: " + pages.size());
    }

}
