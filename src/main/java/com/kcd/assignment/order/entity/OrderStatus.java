package com.kcd.assignment.order.entity;

public enum OrderStatus {
    PENDING, // 주문 대기
    PAID, // 결제 완료
    COMPLETED, // 정상 완료(결제 및 상품픽업 완료)
    CANCELED, // 주문 취소
    REFUNDED, // 환불
}