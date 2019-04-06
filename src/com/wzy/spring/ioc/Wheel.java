package com.wzy.spring.ioc;

public class Wheel {
		private String brand;
	    private String specification ;
		public Wheel(String brand, String specification) {
			super();
			this.brand = brand;
			this.specification = specification;
		}
		public Wheel() {
			super();
		}
		public String getBrand() {
			return brand;
		}
		public void setBrand(String brand) {
			this.brand = brand;
		}
		public String getSpecification() {
			return specification;
		}
		public void setSpecification(String specification) {
			this.specification = specification;
		}
		@Override
		public String toString() {
			return "Wheel [brand=" + brand + ", specification=" + specification
					+ "]";
		}
	    
}
