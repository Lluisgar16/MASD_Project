package masd.agent;

public class Order {
	
	private String name;
	private long timeToCook;
	private String customer;
	private boolean toKitchen;
	private long timeToDeliver;
	private boolean isDelivered;
	
	public long getTimeToDeliver() {
		return timeToDeliver;
	}

	public void setTimeToDeliver(long timeToDeliver) {
		this.timeToDeliver = timeToDeliver;
	}

	public Order(String name, long timeToCook) {
		super();
		this.name = name;
		this.timeToCook = timeToCook;
		this.toKitchen = true;
		this.isDelivered = false;
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

	public long getTimeToCook() {
		return timeToCook;
	}

	public void setTimeToCook(long timeToCook) {
		this.timeToCook = timeToCook;
	}
	
	

}
