package com.ky;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.ky.domain.Employee;
import com.ky.domain.FullName;
import com.ky.domain.Position;

public class RowMapperFactory{

	public RowMapper<Employee> employeeRowMapper() {
		return resultSet -> {
			try {
				BigInteger id = new BigInteger(resultSet.getString("ID"));
				String firstName = resultSet.getString("FIRSTNAME");
				String lastName = resultSet.getString("LASTNAME");
				String middleName = resultSet.getString("MIDDLENAME");
				Position position = Position.valueOf(resultSet.getString("POSITION").toUpperCase());
				LocalDate hireDate = resultSet.getDate("HIREDATE").toLocalDate();
				BigDecimal salary = resultSet.getBigDecimal("SALARY");

				// Create FullName instance
				FullName fullName = new FullName(firstName, lastName, middleName);

				// Return Employee instance
				return new Employee(id, fullName, position, hireDate, salary);
			} catch (SQLException e) {
				throw new RuntimeException("Error mapping ResultSet to Employee", e);
			}
		};
	}

}
