package gov.utah.iris.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * BaseModel class for other models to build upon
 * Accepts a type parameter of T, i.e. Long
 * 
 * @param <T>
 */
@MappedSuperclass
public class BaseModel<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final Long TRUE = 1L;

	public static final Long FALSE = 0L;

	/**
	 * Flag to indicate JPA state of the entity
	 */
	@Transient
	protected boolean persisted;

	/**
	 * Default Constructor
	 */
	public BaseModel() {
		persisted = false;
	}

	/**
	 * Getter for the persisted property.
	 * Since we are not using a common ID in this class, for all sub-classes to utilize, we'll use this getter to accomplish this
	 * Should be overridden by subclasses, to help manage JPA state.
	 * The overriding code should be something as simple as returning whether or not the generated database ID exists
	 * 
	 * @return
	 */
	public boolean isPersisted() {
		return persisted;
	}

	/**
	 * Setter for the persisted property
	 * 
	 * @param persisted
	 */
	public void setPersisted(boolean persisted) {
		this.persisted = persisted;
	}

}
