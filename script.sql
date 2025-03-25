create table users(
	id varchar(255) not null primary key,
	username varchar(255) unique not null,
	role varchar(255) not null,
	password varchar(255) not null
);

create table messages(
	id varchar(255) not null primary key,
	sender_id varchar(255) not null,
	receiver_id varchar(255) not null,
	content text not null,
	created_at timestamp without time zone default current_timestamp,
	updated_at timestamp without time zone,
	read boolean default false,

	CONSTRAINT fk_messages_sender_id FOREIGN KEY (sender_id) REFERENCES users(id),
	CONSTRAINT fk_messages_receiver_id FOREIGN KEY (receiver_id) REFERENCES users(id)
);