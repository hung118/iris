package gov.utah.iris.bean;

/**
 * This class is a bean that represents a page of static site data.
 * It contains a name and whither or not it is the default page.
 * It also has the key to be used to find the menu text in a message resource bundle.
 * Also it contains the link to the content html.
 * Date: May 18, 2005
 */
public class Page {

    private String pageName = "";
    private boolean isDefaulPage = false;
    private String menuKey = "";
    private String contentPath = ""; // consider using URL
    private String titleKey = "";

    /**
     * Returns the name of this page
     * @return String - page name
     */
    public String getPageName() {
        return pageName;
    }

    /**
     * Sets the name of this page to the specified page name
     * @param pageName - String page name
     */
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    /**
     * Returns true if this page is marked as the default page
     * @return boolean - true if this is the default page
     */
    public boolean isDefaulPage() {
        return isDefaulPage;
    }

    /**
     * Sets this page to be the default page
     * @param defaulPage - boolean representing if this is the default page
     */
    public void setDefaulPage(boolean defaulPage) {
        isDefaulPage = defaulPage;
    }

    /**
     * Returns the key to be used to lookup the menu text in a message resource bundle
     * @return String - menu text key
     */
    public String getMenuKey() {
        return menuKey;
    }

    /**
     * Sets the menu text key to be the specified key
     * @param menuKey - String key
     */
    public void setMenuKey(String menuKey) {
        this.menuKey = menuKey;
    }

    /**
     * Returns the path of the content of the page
     * @return String - path of the content
     */
    public String getContentPath() {
        return contentPath;
    }

    /**
     * Sets the path for the content to be the specified path
     * @param contentPath - String path of content
     */
    public void setContentPath(String contentPath) {
        this.contentPath = contentPath;
    }

    /**
     * Returns the title key of the page
     * @return String - title key
     */
    public String getTitleKey() {
        return titleKey;
    }

    /**
     * Set the title key to the specified key
     * @param titleKey - String key
     */
    public void setTitleKey(String titleKey) {
        this.titleKey = titleKey;
    }

    /**
     * Returns a string representation of the page
     * @return
     */
    public String toString() {

        StringBuffer output = new StringBuffer();
        output.append( "\nPage Name: " + pageName );
        output.append( "\nDefault Page: " + isDefaulPage );
        output.append( "\nMenu Key: " + menuKey );
        output.append( "\nContent Path: " + contentPath );
        output.append( "\nTitle Key: " + titleKey );

        return output.toString();
    }
}