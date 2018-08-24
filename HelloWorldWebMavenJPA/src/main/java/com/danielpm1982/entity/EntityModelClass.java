package com.danielpm1982.entity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="EntityModelClass")
public class EntityModelClass {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	@Id
	private long id;
	@Column(name="Entity_Description")
	@NotNull(message="Description cannot be null !! Type something !!")
	private String entityDescription;
	@Column(name="Date_Time")
	private LocalDateTime dateTime;
	@Transient
	private String dateTimeStringified;
	public EntityModelClass() {
		this(null);
	}
	public EntityModelClass(String description) {
		id=-1;
		dateTime=LocalDateTime.now();
		dateTimeStringified=dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM));
		this.entityDescription=description;
	}
	public long getId() {
		return id;
	}
	public String getEntityDescription() {
		return entityDescription;
	}
	public void setEntityDescription(String entityDescription) {
		this.entityDescription = entityDescription;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public String getDateTimeStringified() {
		return dateTimeStringified;
	}
	@Override
	public String toString() {
		return this.getClass().getSimpleName()+" id: "+id+" entity_description: "+entityDescription+" dateTime: "+dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM));
	}
}
