package gov.utah.iris.model;

import gov.utah.dts.det.util.CompareUtils;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class AlertId implements Serializable, Comparable<AlertId> {

	private static final long serialVersionUID = 1L;

	private String name;
	private String code;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AlertId)) {
			return false;
		}
		AlertId other = (AlertId) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		return true;
	}
	@Override
	public int compareTo(AlertId o) {
		if (this == o) {
			return 0;
		}
		int comp = CompareUtils.nullSafeComparableCompare(this.name, o.name, true);
		if (comp == 0) {
			comp = CompareUtils.nullSafeComparableCompare(this.code, o.code, true);
		}
		return comp;	
	}
}
