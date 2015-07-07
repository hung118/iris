package gov.utah.iris.bean;

import java.util.LinkedHashMap;

/**
 * This class is a bean that represents configuration data for static site content.
 * It can contain multiple sections that could be represented by tabs on a site.
 * This bean can also track which section is the default section.
 * Date: May 20, 2005
 */
@SuppressWarnings("rawtypes")
public class StaticConfig {

	private String defaultSectionName = "";

	private LinkedHashMap sections = new LinkedHashMap();

    /**
     * Returns the default seciton name
     * @return String - defalut section name
     */
    public String getDefaultSectionName() {
        return defaultSectionName;
    }

    /**
     * Sets the default section name to the specified section name
     * @param defaultSectionName - String
     */
    public void setDefaultSectionName(String defaultSectionName) {
        this.defaultSectionName = defaultSectionName;
    }

    /**
     * Returns a HashMap. The key is the name of the section and the value is a Section object
     * @return HashMap - sections map
     */
    public LinkedHashMap getSections() {
        return sections;
    }

    /**
     * Sets the sections to be the specified sections HashMap
     * @param sections - HashMap of sections where the keys are the names of section and the value is a Section object
     */
    public void setSections(LinkedHashMap sections) {
        this.sections = sections;
    }

    /**
     * Returns the specified section
     * @param sectionName - The name for the section to be returned
     * @return Section - the specified section
     */
    public Section getSection( String sectionName ) {
        return (Section) sections.get( sectionName );
    }

    /**
     * Returns a string of the status of the section
     * @return String - current status of the object
     */
    public String toString() {

        String defaultSectionNameStr = "\nDefault Section Name: " + defaultSectionName;
        String sectionsSize = "\nNumber of sections: " + sections.size();

        return defaultSectionNameStr + sectionsSize;
    }

    /**
     * Prints the status of the current StaticConfig object
     */
    public void print() {
        System.out.println("\nDefault Section Name: " + defaultSectionName);
        System.out.println("\nNumber of sections: " + sections.size());
    }
}
