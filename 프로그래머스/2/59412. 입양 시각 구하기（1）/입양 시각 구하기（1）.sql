SELECT DATE_FORMAT(DATETIME, '%H') HOUR, COUNT(*) COUNT
FROM ANIMAL_OUTS
WHERE DATE_FORMAT(DATETIME, '%H') BETWEEN 9 AND 19
GROUP BY HOUR
ORDER BY HOUR