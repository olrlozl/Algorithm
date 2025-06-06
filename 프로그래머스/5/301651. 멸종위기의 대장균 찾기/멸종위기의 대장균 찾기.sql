WITH RECURSIVE GENERATIONS AS (
    SELECT ID, PARENT_ID, 1 AS GENERATION
    FROM ECOLI_DATA
    WHERE PARENT_ID IS NULL
    
    UNION ALL
    
    SELECT E.ID, E.PARENT_ID, G.GENERATION + 1
    FROM ECOLI_DATA E JOIN GENERATIONS G ON E.PARENT_ID = G.ID
)

SELECT COUNT(*) AS COUNT, GENERATION
FROM GENERATIONS G
WHERE NOT EXISTS
    (SELECT 1
    FROM ECOLI_DATA E
    WHERE E.PARENT_ID = G.ID)
GROUP BY GENERATION
ORDER BY GENERATION