package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private static SelenideElement amount = $("[data-test-id=amount] input");
    private static SelenideElement from = $("[data-test-id=from] input");
    private static SelenideElement transferButtom = $("[data-test-id=action-transfer]");

    private static SelenideElement errorMessage = $("[data-test-id=error-notification] .notification__content");
    private static SelenideElement header = $(byText("Пополнение карты"));

    public TransferPage() {
        header.shouldBe(visible);
    }

    public DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer (amountToTransfer, cardInfo);
        return new DashboardPage();
    }

    public void makeTransfer (String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amount.setValue(amountToTransfer);
        from.setValue(cardInfo.getCardNumber());
        transferButtom.click();
    }

    public void findErrorMessage(String text) {
        errorMessage.shouldHave(exactText(text), Duration.ofSeconds(10)).shouldBe(visible);
    }
}
