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
                with new_subject as (
                INSERT INTO public.subject(subject_key, dob)
                VALUES (nextval('subject_key'), '2015-01-26')
                returning subject_key
                )
                INSERT INTO public.consent(
                consent_key, subject_key, consent_type_key, start_time)
                VALUES (nextval('consent_key'), (select subject_key from new_subject), 3, current_timestamp);            
  end loop;
end;
$$
;
```
