import java.util.*;

class Solution {
    public int diff(String in, String out) {
        int in_h = Integer.parseInt(in.split(":")[0]);
        int in_m = Integer.parseInt(in.split(":")[1]);
        int out_h = Integer.parseInt(out.split(":")[0]);
        int out_m = Integer.parseInt(out.split(":")[1]);
        
        if (out_m < in_m) {
            out_m += 60;
            out_h--;
        }
        
        return (out_m - in_m) + 60 * (out_h - in_h);
    }
    
    public int calFee(int[] fees, int parkTime) {
        if (parkTime <= fees[0]) return fees[1];
        return fees[1] + (int)(Math.ceil( (float)(parkTime-fees[0]) / fees[2] )) * fees[3];
    }
    
    public int[] solution(int[] fees, String[] records) {
        HashMap<String, Integer> car_park = new HashMap<>();
        HashMap<String, String> car_in = new HashMap<>();
        HashMap<String, Integer> car_fee = new HashMap<>();
        
        for (String record : records) {
            String time = record.split(" ")[0];
            String car = record.split(" ")[1];
            
            if (car_in.containsKey(car)) { // 입차 내역이 있다면
                car_park.put(car, car_park.getOrDefault(car, 0) + diff(car_in.get(car), time)); // 주차 시간 계산 후 누적
                car_in.remove(car); // 입차 내역 삭제
            } 
            else { // 입차 내역이 없다면
                car_in.put(car, time); // 입차 시간 저장
            }
        }
        
        // 출차 내역이 없는 차가 있다면
        for (String car : car_in.keySet()) {
            car_park.put(car, car_park.getOrDefault(car, 0) + diff(car_in.get(car), "23:59")); // 23:59에 출차되었다고 간주하고 주차 시간 계산 후 누적
        }
        
        // 주차요금 계산
        for (String car : car_park.keySet()) {
            car_fee.put(car, calFee(fees, car_park.get(car)));
        }
        
        
        int[] result = new int[car_fee.size()];
        
        // 차량번호 오름차순 정렬
        List<String> carList = new ArrayList<>(car_fee.keySet());
        Collections.sort(carList);
        
        for (int i = 0; i < carList.size(); i++) {
            result[i] = car_fee.get(carList.get(i));
        }
        
        return result;
    }
}