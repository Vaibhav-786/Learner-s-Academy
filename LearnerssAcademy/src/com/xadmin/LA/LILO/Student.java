package com.xadmin.LA.LILO;

public class Student {

	 protected int id;
	    protected String studentname;
	    protected String classname;
	    
	    
	    public Student() {
	    }
	 
	    public Student(int id) {
	        this.id = id;
	    }
	 
	    public Student(int id, String studentname, String classname) {
	        this(studentname, classname);
	        this.id = id;
	    }
	     
	    public Student(String studentname, String classname) {
	        this.studentname = studentname;
	        this.classname = classname;
	        
	    }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getStudentname() {
			return studentname;
		}

		public void setStudentname(String studentname) {
			this.studentname = studentname;
		}

		public String getClassname() {
			return classname;
		}

		public void setClassname(String classname) {
			this.classname = classname;
		}
	 
	    
	    
	    
	    
	    
	
	
}
