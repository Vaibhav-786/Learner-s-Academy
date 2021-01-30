package com.xadmin.LA.LILO;

public class Subject {
	
	protected int id;
    protected String subjectname;
    
    public Subject() {
    }
 
    public Subject(int id) {
        this.id = id;
    }
 
    public Subject(int id, String subjectname) {
        this(subjectname);
        this.id = id;
    }
     
    public Subject(String subjectname) {
        this.subjectname = subjectname;
        
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}
    
    
    
    
    

}
