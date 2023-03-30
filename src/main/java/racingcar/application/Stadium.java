package racingcar.application;

import racingcar.domain.Car;
import racingcar.domain.CarCollection;
import racingcar.dto.RacingCarResultDto;

import java.util.*;
import java.util.stream.Collectors;

public class Stadium {
    private final CarCollection carCollection;
    private final int totalRound;
    private final StadiumMoveOption stadiumMoveOption;
    private int round;

    public Stadium(CarCollection carCollection, int totalRound){
        this(carCollection, totalRound, new RandomStadiumMoveOptionImpl());
    }

    public Stadium(CarCollection carCollection, int totalRound, StadiumMoveOption stadiumMoveOption){
        this.carCollection = carCollection;
        this.totalRound = totalRound;
        this.stadiumMoveOption = stadiumMoveOption;

        this.round = 0;
    }

    public List<Car> racingCars(){

        if(isRacingEnd()){
            throw new IllegalCallerException("이미 경기는 종료되었습니다.");
        }

        round++;

        for(Car car : carCollection.getCars()){
            car.move(stadiumMoveOption.getValue());
        }

        return carCollection.getCars();
    }

    private boolean isRacingEnd() {
        return totalRound <= round;
    }

    private List<Car> getWinner() {
        final int maxPosition = getMaxCarPosition();

        return getSpecificLocationCars(maxPosition);
    }

    public int getMaxCarPosition() {
        return carCollection.getCars().stream()
                .mapToInt(x -> x.getPosition())
                .max()
                .orElseThrow(NoSuchElementException::new);
    }

    public List<Car> getSpecificLocationCars(int position) {
        return carCollection.getCars().stream()
                .filter(car -> car.equalsPosition(position))
                .collect(Collectors.toList());
    }

    public int getRound() {
        return round;
    }

    public RacingCarResultDto playRacingCar(){
        // 경기를 돌리는리는거고
        while(!isRacingEnd()){
            racingCars();
        }

        // 승자를 받기
        String winners = getWinner().stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));

        List<Car> cars = carCollection.getCars();

        return new RacingCarResultDto(winners, cars);
    }

    public List<Car> getCars(){
        return carCollection.getCars();
    }

}
