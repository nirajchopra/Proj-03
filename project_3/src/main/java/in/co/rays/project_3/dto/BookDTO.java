package in.co.rays.project_3.dto;

public class BookDTO extends BaseDTO{

     private String bookName;
     private int bookPrice;
     private String libraryName;
     private String status;

     public String getBookName(){
          return bookName;
     }
     public void setBookName(String bookName){
          this.bookName = bookName;
     }

     public int getBookPrice(){
          return bookPrice;
     }
     public void setBookPrice(int bookPrice){
          this.bookPrice = bookPrice;
     }

     public String getLibraryName(){
          return libraryName;
     }
     public void setLibraryName(String libraryName){
          this.libraryName = libraryName;
     }

     public String getStatus(){
          return status;
     }
     public void setStatus(String status){
          this.status = status;
     }

     @Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
     
}
