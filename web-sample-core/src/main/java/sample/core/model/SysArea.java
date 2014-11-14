package sample.core.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sys_area")
public class SysArea {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String type;

	@Column(name = "area_key")
	private String areaKey;

	@Column(name = "area_value")
	private String areaValue;

	@Column(name = "parent_key")
	private String parentKey;

	@Column(name = "deleted")
	private String deleted;

	@Column(name = "operator_id")
	private Integer operatorId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "operate_date")
	private Date operateDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAreaKey() {
		return areaKey;
	}

	public void setAreaKey(String areaKey) {
		this.areaKey = areaKey;
	}

	public String getAreaValue() {
		return areaValue;
	}

	public void setAreaValue(String areaValue) {
		this.areaValue = areaValue;
	}

	public String getParentKey() {
		return parentKey;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
}
