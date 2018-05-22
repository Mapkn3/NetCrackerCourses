/*1*/
/*
SELECT MIN(sal) "Min", AVG(sal) "Avg", MAX(sal) "Max", SUM(sal) "Sum" FROM EMP;
*/
/*2*/
/*
SELECT COUNT(comm) "count" FROM EMP;
*/
/*3*/
/*
SELECT deptno, MIN(sal) FROM EMP
GROUP BY deptno
HAVING MIN(sal) >= 1000;
*/
/*4*/
/*
SELECT MIN(AVG(sal + NVL(comm, 0))) Minimal FROM EMP
GROUP BY deptno;
*/
/*5*/
/*
SELECT empno, ename FROM EMP
WHERE mgr = (SELECT mgr FROM EMP
             WHERE EMPNO = 2221);
*/
/*6*/
/*
SELECT empno, ename, sal FROM EMP
WHERE sal > (SELECT AVG(sal) FROM EMP);
*/
/*7*/
/*
SELECT ename, deptno, sal FROM EMP
WHERE (deptno, sal) IN (SELECT deptno, sal FROM EMP
                        WHERE COMM IS NOT NULL);
*/
/*8*/
/*
SELECT ename, deptno, sal FROM EMP
WHERE deptno IN (SELECT deptno FROM EMP
                        WHERE COMM IS NOT NULL)
AND sal IN (SELECT sal FROM EMP
                        WHERE COMM IS NOT NULL);
*/
/*9*/
/*
SELECT empno, ename FROM EMP
WHERE sal > ALL (SELECT sal FROM EMP
                 WHERE job = 'CLERK');
*/
/*10*/
/*
SELECT COUNT(empno) Total_count FROM EMP
WHERE deptno = (SELECT deptno FROM DEPT
                WHERE loc = 'DALLAS');
*/
/*11*/
/*
SELECT COUNT(empno) Manager_count FROM EMP
WHERE job = 'MANAGER';
*/
/*12*/
/*
SELECT ename, LEVEL, SYS_CONNECT_BY_PATH(ename, '/') AS manager FROM emp
START WITH mgr IS NULL
CONNECT BY mgr = PRIOR empno;
*/
/*13*/
/*
SELECT e.ename, d.dname, d.deptno, LEVEL, SYS_CONNECT_BY_PATH(e.ename, '/') AS manager FROM emp e, (SELECT dname, deptno FROM dept) d
WHERE d.deptno = e.deptno
START WITH mgr IS NULL
CONNECT BY mgr = PRIOR empno;
*/
/*14*/
/*
SELECT e.ename, d.dname, d.deptno, LEVEL, SYS_CONNECT_BY_PATH(e.ename, '/') AS manager FROM emp e, (SELECT dname, deptno FROM dept) d
WHERE d.deptno = e.deptno
START WITH mgr IS NULL
CONNECT BY mgr = PRIOR empno
ORDER SIBLINGS BY d.dname;
*/
/*15*/
/*
SELECT ename, LEVEL, SYS_CONNECT_BY_PATH(PRIOR ename, '/') AS manager FROM emp
START WITH mgr IS NULL
CONNECT BY mgr = PRIOR empno;
*/