package com.xadmin.LA.LILO;

public class Clas {
	
	protected int id;
    protected String classname;
    
    public Clas() {
    }
 
    public Clas(int id) {
        this.id = id;
    }
 
    public Clas(int id, String classname) {
        this(classname);
        this.id = id;
    }
     
    public Clas(String classname) {
        this.classname = classname;
        
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClasname() {
		return classname;
	}

	public void setClasname(String classname) {
		this.classname = classname;
	}
    
    
    
    
    

}
