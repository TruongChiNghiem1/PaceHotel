package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Service")
public class Service implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5611641200223311937L;
	@Id
	@Column(nullable = false)
	private String serviceID;
	private String serviceName;
	private double price;
	
	@OneToMany(mappedBy = "service")
	private List<BillDetail> billDetail;

	public Service() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Service(String serviceID, String serviceName, double price) {
		super();
		this.serviceID = serviceID;
		this.serviceName = serviceName;
		this.price = price;
	}

	public Service(String serviceID, String serviceName, double price, List<BillDetail> billDetail) {
		super();
		this.serviceID = serviceID;
		this.serviceName = serviceName;
		this.price = price;
		this.billDetail = billDetail;
	}

	public String getServiceID() {
		return serviceID;
	}

	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<BillDetail> getBillDetail() {
		return billDetail;
	}

	public void setBillDetail(List<BillDetail> billDetail) {
		this.billDetail = billDetail;
	}

	@Override
	public String toString() {
		return "Service [serviceID=" + serviceID + ", serviceName=" + serviceName + ", price=" + price + ", billDetail="
				+ billDetail + "]";
	}

	public boolean equalsIgnoreCase(String string) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
