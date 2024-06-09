package printer;

public class Printer {
	private boolean colorPrint;
	private String type, name, producer, printFormat, typeConnect;
	private float price;
	private int speedPrint;
	public void Print(String text, String color) {
	    if(colorPrint) {
		String colorCode;
	    switch (color.toLowerCase()) {
	        case "red":
	            colorCode = "\u001B[31m";
	            break;
	        case "green":
	            colorCode = "\u001B[32m";
	            break;
	        case "blue":
	            colorCode = "\u001B[34m";
	            break;
	        case "yellow":
	            colorCode = "\u001B[33m";
	            break;
	        case "magenta":
	            colorCode = "\u001B[35m";
	            break;
	        case "cyan":
	            colorCode = "\u001B[36m";
	            break;
	        case "white":
	            colorCode = "\u001B[37m";
	            break;
	        default:
	            colorCode = "\u001B[30m"; // black
	    	}
	    System.out.println(colorCode + text + "\u001B[0m");
	    }
	    else
	    	System.out.println("Your printer doesn`t print color text");
	}
	public void Print(String text) {
		System.out.println("\u001B[30m" + text + "\u001B[0m");
	}
	public boolean isColorPrint() {
		return colorPrint;
	}
	public void setColorPrint(boolean colorPrint) {
		this.colorPrint = colorPrint;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public String getPrintFormat() {
		return printFormat;
	}
	public void setPrintFormat(String printFormat) {
		this.printFormat = printFormat;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) throws Exception{
		if(price < 0)
			throw new Exception("Price bigger than 0!");
		else
			this.price = price;
	}
	public int getSpeedPrint() {
		return speedPrint;
	}
	public void setSpeedPrint(int speedPrint) throws Exception {
		if(speedPrint < 0)
			throw new Exception("Speed printing bigger than 0!");
		else
			this.speedPrint = speedPrint;
	}
	public String getTypeConnect() {
		return typeConnect;
	}
	public void setTypeConnect(String typeConnect) {
		this.typeConnect = typeConnect;
	}
}
