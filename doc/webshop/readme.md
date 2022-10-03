## Jakarta EE overview
![jakarta overview](jakarta_overview.drawio.png)

### avoid sqli

ORM / prepared statements:

JPA (Query Builder)

        EntityManager entityManager = EntityManagerfactory.createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> rootEntry = cq.from(Employee.class);

        Metamodel m = entityManager.getMetamodel();
        EntityType<Employee> employee_ = m.entity(Employee.class);
        cq.select(rootEntry).where(
                cb.or(
                    cb.equal(rootEntry.get("eid"), searchRequest.queryId),
                    cb.like(rootEntry.get("ename"), searchRequest.queryName )));

        TypedQuery<Employee> query = entityManager.createQuery(cq);
        return query.getResultList();

JPA (own query language)

         List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e WHERE e.ename LIKE :ename or e.eid = :eid")
                .setParameter("ename", sr.queryName)
                .setParameter("eid", sr.queryId).getResultList();
        return employees;

don't 

        entityManager.createNativeQuery("SELECT * FROM Employee WHERE ename = " + sr.queryName)

Prepared statement 

        prepStmt = con.prepareStatement("select eid, ename from Employee where ename like ? or eid = ?");
        prepStmt.setString(1, sr.queryName);
        prepStmt.setInt(2, sr.queryId);
        rs = prepStmt.executeQuery();

        List<Employee> employeeList = new ArrayList<>();
        while (rs.next()) {
            int empid = rs.getInt("eid");
            String name = rs.getString("ename");
            employeeList.add(new Employee(empid, name, 0, "test"));
        }

don't

    stmt = con.createStatement();
    rs = stmt.executeQuery("select eid, ename from Employee " +
                                    "where ename like '%" + sr.queryName + "%' or eid = " + sr.queryId);
