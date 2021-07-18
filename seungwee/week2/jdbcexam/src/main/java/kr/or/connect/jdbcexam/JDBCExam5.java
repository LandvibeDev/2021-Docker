package kr.or.connect.jdbcexam;

import kr.or.connect.jdbcexam.dao.RoleDao5;
import kr.or.connect.jdbcexam.dto.Role;

public class JDBCExam5 {
	public static void main(String[] args) {
//수정테스트
		int roleId = 500;
		String description = "CEO";
		
		Role role = new Role(roleId, description);
		
		RoleDao5 dao = new RoleDao5();
		int updateCount = dao.updateRole(role);

		System.out.println(updateCount);
	} 
}