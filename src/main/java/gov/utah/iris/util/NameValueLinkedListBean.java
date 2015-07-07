package gov.utah.iris.util;

import java.util.*;

/**
 * Date: Oct 21, 2005
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class NameValueLinkedListBean {

    private String name = "";
    private String value = "";
	private Collection children = new ArrayList();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Collection getChildren() {
        return this.children;
    }

    public void setChildren(Collection children) {
        this.children = children;
    }


    /**
     *
     * @param child
     */
    public void addChild( NameValueLinkedListBean child ) {
        children.add(child);
    }


    /**
     *
     * @param name
     * @return
     */
    public Collection getChild(String name) {

        Collection result = new ArrayList();

        Iterator iterator = children.iterator();
        while ( iterator.hasNext() ) {

            NameValueLinkedListBean child = (NameValueLinkedListBean)iterator.next();

            if ( child != null && child.getName().equals(name) ) {
                result.add(child);
            }
        }

        return result;

    }


    /**
     *
     * @param name
     * @return
     */
    public String getChildValue( String name ) {

        String result = null;

        ArrayList children = (ArrayList)getChild(name);

        if ( children != null && children.size() > 0 ) {

            NameValueLinkedListBean child = (NameValueLinkedListBean)children.get(0);

            if ( child != null ) {
                result = child.getValue();
            }
        }

        return result;
    }


    /**
     *
     * @return
     */
    public boolean hasChildren() {

        boolean result = false;

        if ( children.size() > 0 )
            result = true;

        return result;
    }


    /**
     *
     * @return
     */
    public Collection getChildrenNames() {

        Map names = new HashMap();

        Iterator childrenIt = children.iterator();
        while ( childrenIt.hasNext() ) {
            NameValueLinkedListBean child = (NameValueLinkedListBean)childrenIt.next();
            names.put(child.getName(),null);
        }

        return names.keySet();
    }


    /**
     *
     * @return
     */
    public int size() {
        return children.size();
    }
}
