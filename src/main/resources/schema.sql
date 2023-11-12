create table employee (
	id bigint not null auto_increment primary key,
	employee_number varchar(255) not null,
	affiliation varchar(255),
	name varchar(255) not null,
	gender varchar(10),
	birthday Date,
	zipcode varchar(8),
	address varchar(255),
	telephone_number varchar(18),
	mail_address varchar(254),
	date_of_employment Date,
	created_at TIMESTAMP not null,
	updated_at TIMESTAMP not null,
	deleted_at TIMESTAMP
);