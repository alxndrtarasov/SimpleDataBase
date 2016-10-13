package objtype;

import java.util.Date;

public class Obj {

	private String id;
	private String name;
	private Date date;
	private String description;

	public Obj(String id, String name, Date date, String description) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Obj [id=" + id + ", name=" + name + ", date=" + date + ", description=" + description + "]";
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
