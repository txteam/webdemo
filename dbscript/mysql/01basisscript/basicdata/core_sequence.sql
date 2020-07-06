drop table if exists core_sequence;     
create table core_sequence(         
	seq_name VARCHAR(50) NOT NULL,			-- 序列名称         
	current_val INT	NOT NULL,				-- 当前值         
	increment_val INT NOT NULL DEFAULT 1,	-- 步长(跨度)         
	PRIMARY KEY (seq_name)   
);

create function currval(v_seq_name VARCHAR(50))     
returns integer    
begin        
    declare value integer;         
    set value = 0;         
    select current_val into value from core_sequence where seq_name = v_seq_name;   
   return value;   
end;

create function nextval(v_seq_name VARCHAR(50))  
    returns integer  
begin  
    update core_sequence set current_val = current_val + increment_val  where seq_name = v_seq_name;  
    return currval(v_seq_name);  
end;

INSERT INTO core_sequence VALUES ('seq_client_info_code', '0', '1');