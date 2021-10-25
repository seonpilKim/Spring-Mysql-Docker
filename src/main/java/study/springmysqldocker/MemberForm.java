package study.springmysqldocker;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String name;

    public Member convert() {
        return new Member(this.name);
    }
}
