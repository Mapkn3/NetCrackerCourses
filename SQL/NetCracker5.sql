
CREATE OR REPLACE PROCEDURE changeSal(
  emp_name emp.ename%TYPE,
  emp_sal emp.sal%TYPE)
IS
BEGIN
  UPDATE emp
    SET sal = emp_sal
    WHERE ename = emp_name;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      DBMS_OUTPUT.put_line(SQLERRM);
END;

/*
DECLARE
  emp_no emp.empno%TYPE := 7839;
  emp_name emp.ename%TYPE;
  emp_sal emp.sal%TYPE;
  emp_hire emp.hiredate%TYPE;
BEGIN
  SELECT ename, sal, hiredate
  INTO emp_name, emp_sal, emp_hire
  FROM emp
  WHERE empno = emp_no;
  IF (SYSDATE - emp_hire) < 10 THEN
    emp_sal := emp_sal * 1.05;
  ELSIF (SYSDATE - emp_hire) >= 10 THEN
    emp_sal := emp_sal * 1.1;
  ELSIF (SYSDATE - emp_hire) >= 15 THEN
    emp_sal := emp_sal * 1.15;
  END IF;
  UPDATE emp
    SET sal = emp_sal
    WHERE empno = emp_no;
END;
*/
/*
CREATE SEQUENCE dept_deptno
INCREMENT BY 1
START WITH 91
MAXVALUE 100
NOCYCLE
NOCACHE;

DECLARE
  v_cnt dept.deptno%TYPE := 3;
  v_name dept.dname%TYPE := 'SALES';
  v_loc dept.loc%TYPE := 'DALLAS';
BEGIN
  FOR i IN 1..v_cnt LOOP
    INSERT INTO dept(deptno, dname, loc)
      VALUES (dept_deptno.NEXTVAL, v_name||TO_CHAR(i), v_loc||TO_CHAR(i));
  END LOOP;
END;
*/