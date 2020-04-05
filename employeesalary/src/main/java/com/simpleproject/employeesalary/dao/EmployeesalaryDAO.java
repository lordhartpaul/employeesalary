 package com.simpleproject.employeesalary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;


import com.simpleproject.employeesalary.model.Employeesalary;



@Service
public class EmployeesalaryDAO {
	
	JdbcTemplate template;
	
	@Autowired
	public void setDataSource(DataSource datasource) {
		template = new JdbcTemplate(datasource);
	}
	
	public void save(Employeesalary p)
	{
		
		String sql = "insert into employeesalary(firstName,lastName,salary) values ('"+p.getFirstName()+"','"+p.getLastName()+"',"+p.getSalary()+")";
	
	    template.update(sql);
	
	}
	
public List<Employeesalary> getAllEmployeesalarys(){
		
		return template.query("select * from employeesalary", new ResultSetExtractor<List<Employeesalary>>() {
			
			public List<Employeesalary> extractData(ResultSet rs) throws SQLException,DataAccessException
			{
				List<Employeesalary> list = new ArrayList<Employeesalary>();
				while(rs.next())
				{
					Employeesalary e = new Employeesalary();
					e.setId(rs.getInt(1));
					e.setFirstName(rs.getString(2));
					e.setLastName(rs.getString(3));
					e.setSalary(rs.getString(4));
					
					list.add(e);
					
					}
				
				return list;
			}
			
		});
	}
	
public List<Employeesalary> getEmployeesalarysByPage(int pageid, int total){
	
	
	String sql = "select * from employeesalary limit "+(pageid-1)+","+total;
	
	return template.query(sql, new ResultSetExtractor<List<Employeesalary>>() {
			
			public List<Employeesalary> extractData(ResultSet rs) throws SQLException,DataAccessException
			{
				List<Employeesalary> list = new ArrayList<Employeesalary>();
				while(rs.next())
				{
					Employeesalary e = new Employeesalary();
					e.setId(rs.getInt(1));
					e.setFirstName(rs.getString(2));
					e.setLastName(rs.getString(3));
					e.setSalary(rs.getString(4));
					
					list.add(e);
					}
				return list;
			}
			
		});
	}
	
public Employeesalary getEmployeesalaryById(int id)
{
	   return template.query("select * from employeesalary where ID="+id,new ResultSetExtractor<Employeesalary>() {
			
			public Employeesalary extractData(ResultSet rs) throws SQLException,DataAccessException
			{
				Employeesalary e = new Employeesalary();
				while(rs.next())
				{
					
					e.setId(rs.getInt(1));
					e.setFirstName(rs.getString(2));
					e.setLastName(rs.getString(3));
					e.setSalary(rs.getString(4));
					}
				return e;
			}
			
		});
	}

public void update(Employeesalary p)
{
	String sql="update employeesalary set firstName ='"+p.getFirstName()+"',lastName='"+p.getLastName()+"',salary='"+p.getSalary()+"' where ID="+p.getId()+"";
    template.update(sql);

}

public void delete(int id) {
	
	String sql="delete from employeesalary where ID="+id+"";
	template.update(sql);
}

public void delete() {
	
	String sql = "delete from employeesalary where ID>0";
	template.update(sql);
}

public int count() {
	String sql ="select count(*) from employeesalary";
	int count = template.queryForObject(sql, Integer.class);
	return count;
}

}
