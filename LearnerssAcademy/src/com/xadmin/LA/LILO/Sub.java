package com.xadmin.LA.LILO;

public class Sub {
	
	 protected int id;
	    protected String subname;
	    protected String classname;
	   
	    public Sub() {
	    }
	 
	    public Sub(int id) {
	        this.id = id;
	    }
	 
	    public Sub(int id, String subname, String classname) {
	        this(subname, classname);
	        this.id = id;
	    }
	     
	    public Sub(String subname, String classname) {
	        this.subname = subname;
	        this.classname = classname;
	        
	    }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getSubname() {
			return subname;
		}

		public void setSubname(String subname) {
			this.subname = subname;
		}

		public String getClassname() {
			return classname;
		}

		public void setClassname(String classname) {
			this.classname = classname;
		}
	    
	 
}
