-- Rappels SQL et requetes de classement sous Oracle

-- ajout de contrainte d'integrités
ALTER TABLE Emp
add CONSTRAINT fk_deptno
  FOREIGN KEY (DEPTNO)
  REFERENCES Dept(DEPTNO);

ALTER TABLE Emp
add CONSTRAINT pk_empno
  PRIMARY KEY (EMPNO);

-- a
select sal from emp where deptno=10 or deptno=30 order by sal;
-- c
select ROUND(AVG(sal)) from emp group by deptno ;
-- d
SELECT  
ROW_NUMBER() OVER(ORDER BY sal DESC) AS "numero", 
deptno, 
sal
FROM emp 
WHERE deptno=10 OR deptno=20 
GROUP BY deptno,sal
ORDER BY deptno;
-- e
SELECT  job, SUM(sal) as salaire_total from emp group by job;
SELECT DISTINCT job  , SUM(sal) over(partition by job) AS salaire_total from emp;

-- f
-- voir le compte rendu

-- g
SELECT sum(sal) as montant_total_salaire_versee from emp;
SELECT deptno,sum(sal) from emp group by deptno;
SELECT job,deptno,sum(sal) from emp group by job,deptno;
