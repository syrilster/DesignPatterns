**Use case 1:** Insert data into a single table. (Below example shows 500 record insertion into measurement table) 

```
do
$$
declare
  i record;
begin
  for i in 1..500 loop
    INSERT INTO table_one (table_one_key, name, description, table_one_type_key)
                VALUES (nextval('table_one_key'), (select md5(random()::text) from generate_Series(1,1) s), '', 1);
  end loop;
end;
$$
;
```

**Use case 2:** Insert data into multiple tables at once. i.e. linked tables. 
```
do
$$
declare
  i record;
begin
  for i in 1..500 loop
                with temp_table as (
                INSERT INTO public.table_one(table_one_key, dob)
                VALUES (nextval('table_one_key'), '2015-01-26')
                returning table_one_key
                )
                INSERT INTO public.table_two(
                table_two_key, table_one_key, table_two_type_key, start_time)
                VALUES (nextval('table_two_key'), (select table_one_key from temp_table), 3, current_timestamp);            
  end loop;
end;
$$
;
```
