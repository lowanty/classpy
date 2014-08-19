package com.github.zxh.classpy.classfile;

import java.util.Collections;
import java.util.List;

/**
 * Abstract base class for all class file components.
 * 
 * @author zxh
 */
public abstract class ClassComponent {
    
    private int offset; // the position of this ClassComponent in class file
    private int length; // how many bytes this ClassComponent has
    private String name;
    private String desc;

    // Getters & Setters
    public final int getOffset() {return offset;}
    public final int getLength() {return length;}
    public final String getName() {return name;}
    public final void setName(String name) {this.name = name;}
    public final String getDesc() {return desc;}
    public final void setDesc(String desc) {this.desc = desc;}

    /**
     * Reads content, records offset and length.
     * @param reader 
     */
    public final void read(ClassReader reader) {
        offset = reader.getPosition();
        readContent(reader);
        length = reader.getPosition() - offset;
    }
    
    /**
     * Reads content using ClassReader.
     * @param reader 
     */
    protected abstract void readContent(ClassReader reader);
    
    /**
     * Returns sub-components.
     * This is the default implementation, subclass which really has sub-components
     * should override this.
     * @return 
     */
    @SuppressWarnings("unchecked")
    public List<? extends ClassComponent> getSubComponents() {
        return Collections.EMPTY_LIST;
    }
    
    /**
     * The returned string is displayed by ClassComponentTreeItem.
     * @return 
     */
    @Override
    public final String toString() {
        if (name != null && desc != null) {
            return name + ": " + desc;
        }
        if (name != null) {
            return name;
        }
        if (desc != null) {
            return desc;
        }
        
        return getClass().getSimpleName();
    }
    
}
