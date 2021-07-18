package kr.or.connect.jdbcexam;

import kr.or.connect.jdbcexam.dao.RoleDao2;
import kr.or.connect.jdbcexam.dto.Role;

public class JDBCExam2 {
	public static void main(String[] args) {
		int roleId = 501;
		String description = "CTO";
		
		Role role = new Role(roleId, description);
		
		RoleDao2 dao = new RoleDao2();
		int insertCount = dao.addRole(role);

		System.out.println(insertCount);
	}
}