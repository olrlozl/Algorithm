# SELECT F.SHIPMENT_ID, F.FLAVOR
# FROM FIRST_HALF F JOIN JULY J ON F.SHIPMENT_ID = J.SHIPMENT_ID
    
SELECT FLAVOR
FROM 
    (SELECT * FROM FIRST_HALF
    UNION
    SELECT * FROM JULY) A
GROUP BY FLAVOR
ORDER BY SUM(TOTAL_ORDER) DESC
LIMIT 3