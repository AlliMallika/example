package customer.model;

public class PaginationParams {
	private int limit;
    private int offset;
    
    
    public PaginationParams() {
    	 // Default constructor
    }
    
	public PaginationParams(int limit, int offset) {
		super();
		this.limit = limit;
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
    
}