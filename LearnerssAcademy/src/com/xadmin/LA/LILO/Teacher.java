package com.xadmin.LA.LILO;

public class Teacher {
	
	protected int id;
    protected String teachername;
    
    public Teacher() {
    }
 
    public Teacher(int id) {
        this.id = id;
    }
 
    public Teacher(int id, String teachername) {
        this(teachername);
        this.id = id;
    }
     
    public Teacher(String teachername) {
        this.teachername = teachername;
        
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTeachername() {
		return teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}
    
    
    
    
    

}
