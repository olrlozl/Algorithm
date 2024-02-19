SELECT B.USER_ID, NICKNAME, TOTAL_SALES
FROM
    (SELECT WRITER_ID, SUM(PRICE) TOTAL_SALES
    FROM USED_GOODS_BOARD
    WHERE STATUS = 'DONE'
    GROUP BY WRITER_ID) A
JOIN USED_GOODS_USER B
ON A.WRITER_ID = B.USER_ID
WHERE TOTAL_SALES >= 700000
ORDER BY TOTAL_SALES