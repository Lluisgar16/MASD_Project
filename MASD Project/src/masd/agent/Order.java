package masd.agent;

public class Order {
	
	private String name;
	private int timeToCook;
	private String customer;
	private boolean toKitchen;
	private int timeToDeliver;
	private boolean isDelivered;
	
	public long getTimeToDeliver() {
		return timeToDeliver*1000;
	}


	public Order(String name, int timeToCook, int timeToDeliver) {
		super();
		this.name = name;
		this.timeToCook = timeToCook;
		this.toKitchen = true;
		this.isDelivered = false;
		this.timeToDeliver = timeToDeliver;
	}

	public boolean isDelivered() {
		return isDelivered;
	}

	public void setDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public boolean isToKitchen() {
		return toKitchen;
	}

	public void setToKitchen(boolean toKitchen) {
		this.toKitchen = toKitchen;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTimeToCook() {
		return timeToCook*1000;
	}

	
	

}
