package com.xadmin.LA.LILO;

public class Record {
	
	
	 protected int id;
	    protected String classname;
	    protected String subjectname;
	    protected String teachername;
	    protected String studentname;
	    
	 
	    public Record() {
	    }
	 
	    public Record(int id) {
	        this.id = id;
	    }
	 
	    public Record(int id, String classname, String subjectname, String teachername,String studentname) {
	        this(classname, subjectname, teachername, studentname);
	        this.id = id;
	    }
	     
	    public Record(String classname, String subjectname, String teachername,String studentname) {
	        this.classname = classname;
	        this.subjectname = subjectname;
	        this.teachername = teachername;
	        this.studentname = studentname;
	        
	    }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getClassname() {
			return classname;
		}

		public void setClassname(String classname) {
			this.classname = classname;
		}

		public String getSubjectname() {
			return subjectname;
		}

		public void setSubjectname(String subjectname) {
			this.subjectname = subjectname;
		}

		public String getTeachername() {
			return teachername;
		}

		public void setTeachername(String teachername) {
			this.teachername = teachername;
		}

		public String getStudentname() {
			return studentname;
		}

		public void setStudentname(String studentname) {
			this.studentname = studentname;
		}
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    

}
