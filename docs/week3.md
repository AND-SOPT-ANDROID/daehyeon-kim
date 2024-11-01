# 3주차 필수 과제

## 과제

- [x] 지금까지 과제로 진행한 뷰에 대해서 실습 때 진행한 플로우를 바탕으로 컴포넌트화 및 UI단 설계를 진행해주세요.
- (홈, MY, 로그인, 회원가입 필수 검색 선택)



| 회원가입                                                                                                                 | 로그인                                                                                                                  | 홈                                                                                                                  | MY                                                                                                                  |
|:----------------------------------------------------------------------------------------------------------------------|:---------------------------------------------------------------------------------------------------------------------|:-------------------------------------------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------------------------------------|
| <img src="https://github.com/user-attachments/assets/843c8ea9-e6ee-4e99-9462-16533232f9aa" alt="회원가입 화면" width="250"> | <img src="https://github.com/user-attachments/assets/7d058ee8-d3db-459c-98c2-99b7c132d879" alt="로그인 화면" width="250"> | <img src="https://github.com/user-attachments/assets/5df0a20f-6e12-4b07-82b5-695dba6eb5ee" alt="홈 화면" width="250"> | <img src="https://github.com/user-attachments/assets/e6b323b0-462f-41ff-8b81-c153a52208ef" alt="MY 화면" width="250"> |

<br>

## 1. 공통 컴포넌트 찾기

<img width="1055" alt="공통 컴포넌트" src="https://github.com/user-attachments/assets/cf552486-2dd2-4698-b767-6a3d22552265">

- 공통 컴포넌트 추출 기준은 두 화면이상 공통적으로 보이는 컴포넌트 기준으로 추출해봤습니다.
- text는 text색상별로 컴포넌트화 해보면 어떨까? 하는 생각으로 한번 추출해 봤습니다.
    - ex) `whiteText()` / `grayText()`

<br>
<img width="1049" alt="스크린샷 2024-11-01 16 01 24" src="">
<img width="1058" alt="스크린샷 2024-11-01 16 02 20" src="">
<img width="1054" alt="스크린샷 2024-11-01 16 02 47" src="">
<img width="1057" alt="스크린샷 2024-11-01 16 03 02" src="">


## 2. 뷰 스케치 및 각 화면 컴포넌트화

| 회원가입                                                                                                                     | 로그인                                                                                                                     |
|:-------------------------------------------------------------------------------------------------------------------------|:------------------------------------------------------------------------------------------------------------------------|
| <img width="500" alt="회원가입 뷰 스케치" src="https://github.com/user-attachments/assets/a2675b7f-952b-4956-b23a-005003941058"> | <img width="500" alt="로그인 뷰 스케치" src="https://github.com/user-attachments/assets/0c3d49c8-e50c-4d1c-8225-07973cc6f175"> |
| 홈                                                                                                                        | MY                                                                                                                      |
| <img width="520" alt="홈 뷰 스케치" src="https://github.com/user-attachments/assets/0ea3b906-3209-4615-8a54-a37c2f5540f4">    | <img width="480" alt="MY 뷰 스케치" src="https://github.com/user-attachments/assets/626cb726-0507-4530-8cd9-ea6716a9addc">  |

<br>

## 3. UI 로직 설계

| 회원가입                                                                                                                        | 로그인                                                                                                                       |
|:----------------------------------------------------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------------------------------------------|
| <img width="500" alt="회원가입 UI 로직 설계" src="https://github.com/user-attachments/assets/b862a7d2-32f3-4d86-99cd-a4f0437808a2"> | <img width="500" alt="로그인 로직설계" src="https://github.com/user-attachments/assets/5fde7f8e-2441-4be6-bf9e-da6de88a6e5b">    |
| 홈                                                                                                                           | MY                                                                                                                        |
| <img width="500" alt="홈 UI 로직 설계" src="https://github.com/user-attachments/assets/6d4c66f1-3fb9-4b33-b2d0-c148831fd228">    | <img width="500" alt="My UI 로직 설계" src="https://github.com/user-attachments/assets/8d40dbfe-96bc-49b4-9fdc-9aba98159155"> |

<br>

## 4. 공통되는 로직 확장함수화

- 오류메시지 출력 부분 (snackbar)

```kotlin
fun ScaffoldState.showSnackbar(
    scope: CoroutineScope,
    message: String,
    duration: SnackbarDuration = SnackbarDuration.Short
) {
    scope.launch {
        snackbarHostState.showSnackbar(
            message = message,
            duration = duration
        )
    }
}
```
