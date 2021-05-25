

CREATE OR REPLACE PROCEDURE count_regions (
   code IN NUMBER,
   total_regions OUT NUMBER )
AS
BEGIN
    SELECT COUNT(*) INTO total_regions
    FROM countries
    WHERE REGION_CODE = code;
END;
/

SET serveroutput on;
DECLARE
    code_In number;
    count_Out varchar2(100);
BEGIN
    code_In := 9;
    count_regions(code_In, count_Out);
    dbms_output.put_line('count_Out:= ' || count_Out);
END;
/

--==================================================================

create or replace NONEDITIONABLE PACKAGE test_pkg AS
   PROCEDURE in_only_test (inParam1 IN VARCHAR2);
   PROCEDURE in_and_out_test (inParam1 IN VARCHAR2, outParam1 OUT VARCHAR2);
   FUNCTION HELLO_WORLD(TEXT VARCHAR2) RETURN VARCHAR2;
   --SELECT  test_pkg.HELLO_WORLD('Hari')  FROM dual;
END test_pkg;

create or replace NONEDITIONABLE PACKAGE BODY test_pkg AS
   PROCEDURE in_only_test(inParam1 IN VARCHAR2) AS
   BEGIN
      DBMS_OUTPUT.PUT_LINE('in_only_test');
   END in_only_test;
   PROCEDURE in_and_out_test(inParam1 IN VARCHAR2, outParam1 OUT VARCHAR2) AS
   BEGIN
      outParam1 := 'Woohoo Im an outparam, and this is my inparam ' || inParam1;
   END in_and_out_test;
   FUNCTION HELLO_WORLD(TEXT VARCHAR2) RETURN VARCHAR2 IS
  BEGIN
    RETURN 'HELLO WORLD ' || TEXT;
  END;
END test_pkg;
--==========================================================================

CREATE OR REPLACE PROCEDURE show_people_data( i_name IN VARCHAR2, o_resp_set OUT SYS_REFCURSOR)
IS
BEGIN
dbms_output.put_line('Hello 1');
OPEN o_resp_set FOR SELECT EMP_ID,FIRST_NAME,LAST_NAME,COMPANY_NAME,ADDRESS,CITY,COUNTRY,ZIP,EMP_STATE from EMPLOYEES e where e.FIRST_NAME IN (i_name);
dbms_output.put_line('Hello 2');
EXCEPTION WHEN OTHERS THEN
dbms_output.put_line('Hello  3');
OPEN o_resp_set FOR SELECT 'something wrong' as error from dual;
END show_people_data;
/
COMMIT;

--============================================================

DROP TABLE PERSON;
DROP SEQUENCE SQ_PERSON;

CREATE TABLE PERSON(
 ID NUMBER(19),
 FIRST_NAME VARCHAR(255),
 LAST_NAME VARCHAR(255),
 ADDRESS VARCHAR(255),
 PRIMARY KEY (ID)
);

CREATE SEQUENCE SQ_PERSON MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 10;
DROP TABLE PERSON_HISTORY;
CREATE TABLE PERSON_HISTORY AS SELECT * FROM PERSON WHERE 1=0;

CREATE OR REPLACE PROCEDURE MOVE_TO_HISTORY(person_id_in IN NUMBER, msg_out OUT VARCHAR2)
IS
 temp_count NUMBER := -1;
BEGIN
   select count(*) into temp_count from PERSON p where p.id = person_id_in;
  IF temp_count > -1  THEN
      insert into PERSON_HISTORY (select * from PERSON where id = person_id_in);
      msg_out := 'Person with id: ' || person_id_in || ' moved to History table. Update count: ' || sql%Rowcount;
      delete from PERSON p where p.id = person_id_in;
  ELSE
   msg_out := 'No Person Exists with id: '|| person_id_in;
 END IF;
END;
/

CREATE OR REPLACE PROCEDURE FETCH_PERSON_HISTORY(history_cursor OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN history_cursor FOR SELECT * FROM PERSON_HISTORY;
END;

--========================================================
CREATE TYPE person_obj AS OBJECT(id NUMBER(7), name VARCHAR2(100));
CREATE TYPE person_array AS VARRAY(10) OF person_obj;
SELECT * FROM TABLE(person_array(person_obj(1, 'Jim'),person_obj(2, 'Joe')));