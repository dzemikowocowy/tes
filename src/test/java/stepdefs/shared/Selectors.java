package stepdefs.shared;

import java.util.HashMap;

/**
 * Created by bbeesley on 02/10/15.
 */
public class Selectors {
    private static class SBSelectorHashMap extends HashMap<String, String> {
        public SBSelectorHashMap() {
            this.put("button.coathangerman", "span.account-tab__text.-login");
            this.put("button.coathangerman.logged-in", "span.account-tab__text.-account");
            this.put("link.priceformat", "#odds-format-filter > a");
            this.put("link.betslip.mobile", "#mobile-betslip-link");
            this.put("link.betslip.desktop", "#open-betslip");
            this.put("coathanger-man.logged-out", "body > header > section > section > div > a > i.icon-account-nli");
            this.put("button.all-races", "button.race-meeting__allRaces");
            this.put("button.racing.meetings", "#meetings-content > div > section > section > section.header-panel.header-panel--off-xlarge > div.header-panel__toolbar > a > span");
            this.put("section.race-event", "section.race-event");
            this.put("button.oddsbutton", "button.oddsbutton[data-status=\"A\"]");
            this.put("overlay.deposit", "iframe.mfp-iframe");
            this.put("overlay.deposit.description", "#s_success > p");
        }
    }
    public static final HashMap<String, String> sbSelectors = new SBSelectorHashMap();
    private static class BSSelectorHashMap extends HashMap<String, String> {
        public BSSelectorHashMap() {
            this.put("title", "#betslip-title");
            this.put("button.place-bet", "#place-bet-button");
            this.put("receipt.header", "#bet-receipt > div > header");
            this.put("text.bstab", "#betslip-tab > a");
            this.put("text.selection-count.desktop", "#betslip-count");
            this.put("text.selection-count.mobile", "#mobile-betslip-count");
            this.put("div.quickdeposit", "#quickdeposit");
            this.put("quick-deposit.title", "#qd-title");
            this.put("quick-deposit.title.close", "#qd-close");
            this.put("quick-deposit.message", "#qd-message");
            this.put("quick-deposit.current-balance", "#qd-balance");
            this.put("quick-deposit.payment-card", "#qd-card-no");
            this.put("quick-deposit.cvv-code", "#qd-card-cvv");
            this.put("quick-deposit.amount", "#qd-amount");
            this.put("quick-deposit.payment-card.title", "label[for='qd-card-no']");
            this.put("quick-deposit.cvv-code.title", "label[for='qd-card-cvv']");
            this.put("quick-deposit.amount.title", "label[for='qd-amount']");
            this.put("quick-deposit.cvv-code.error", "#qd-cvv-err");
            this.put("quick-deposit.amount.error", "#qd-amount-err");
            this.put("quick-deposit.add-button", "button.qd-found-add");
            this.put("cvv-icon", "span.icon-cvv");
            this.put("quick-deposit.deposit-button", "#qd-submit");
            this.put("quick-deposit.cancel", "#qd-cancel");
            this.put("quick-deposit.receipt", "#qd-receipt");
            this.put("quick-deposit.receipt.title", "#qd-receipt > header");
            this.put("bet-receipt.title", "#betslip-title-receipt");
            this.put("bet-receipt.transaction-ref", "#qd-receipt-details");
            this.put("quick-deposit.receipt.balance-message", "#qd-new-balance");
            this.put("quick-deposit.receipt.card-section", "section.card-section");
            this.put("quick-deposit.receipt.transaction-details", "#qd-receipt-details");
            this.put("quick-deposit.receipt.transaction-details.ref", "#qd-receipt-details");
            this.put("quick-deposit.receipt.transaction-details.title", "#qd-receipt-details > tbody > tr:nth-child(1) > th");
            this.put("quick-deposit.iframe.3ds", "#qd-tds-iframe");
            this.put("quick-deposit.iframe.3ds.body", "form[name=\"postPAResToMPIForm\"]");
            this.put("quick-deposit.iframe.3ds.submit", "input[value=\"Submit\"]");
            this.put("x-icon", "span.icon-x");
            this.put("estimated-returns.link", "a.estimated-returns-link");
            this.put("bet-receipt.estimated-returns.link", "a[href=\"javascript:showHelp('inline',1,5656);\"]");
            this.put("bet-receipt.body", "#bet-receipt");
            this.put("error.maximum.selections", "");
            this.put("error.maximum.selections", "");
            this.put("error.maximum.selections", "");
            this.put("error.price.change", "#error-box-footer");
            this.put("error.low.funds", "#error-box-header");
            this.put("error.low-funds.deposit-link", "#error-box-header > a");
            this.put("error.high.stake", "#error-box-header");
            this.put("error.low.stake", "#error-box-header");
            this.put("error.empty.stake", "#error-box-header");
            this.put("error.handicap.changed","#error-box-header");
            this.put("tabs.open-bets", "#openbets-tab > a");
            this.put("tabs.betslip-tab", "#betslip-tab > a");
            this.put("open-bets.est-returns.accumulator", "#bet-receipt > div.selected-bet > div.selection-footer");
            this.put("open-bets.button.cimb-alert", "input.cimb-alert-button");
            this.put("open-bets.bet", "div.cashout-bet-inner");
            this.put("open-bets.event-name", "div.cashout-link.cashout-row > a");
            this.put("open-bets.show-details", "a[data-ng-hide=\"bet.expandedDetails\"]");
            this.put("open-bets.hide-details", "a[data-ng-show=\"bet.expandedDetails\"]");
            this.put("open-bets.top-bet", "#openbets-content > div.cashout-bets-wrapper.ng-scope > div:nth-child(1)");
            this.put("div.stake.accumulator", "div.multiple-bet");
            this.put("checkbox.each-way", "input[data-ng-model=\"bet.each_way\"]");
            this.put("button.open-bets.more", "#cashout-more");
            this.put("stake.double", "#stake-input_DBL");
            this.put("select.double", "#multiple-type-dbl");
            this.put("stake.trixie", "#stake-input_TRX");
            this.put("select.trixie", "#multiple-type-trx");
            this.put("stake.patent", "#stake-input_PAT");
            this.put("select.patent", "#multiple-type-pat");
            this.put("select.treble", "#multiple-type-tbl");
            this.put("stake.treble", "#stake-input_TBL");
            this.put("select.yankee", "#multiple-type-yan");
            this.put("stake.yankee", "#stake-input_YAN");
            this.put("select.lucky15", "#multiple-type-l15");
            this.put("stake.lucky15", "#stake-input_L15");
            this.put("select.canadian", "#multiple-type-can");
            this.put("stake.canadian", "#stake-input_CAN");
            this.put("select.lucky31", "#multiple-type-l31");
            this.put("stake.lucky31", "#stake-input_L31");
            this.put("select.fourfolds", "#multiple-type-acc4");
            this.put("stake.fourfolds", "#stake-input_ACC4");
            this.put("select.heinz", "#multiple-type-hnz");
            this.put("stake.heinz", "#stake-input_HNZ");
            this.put("select.lucky63", "#multiple-type-l63");
            this.put("stake.lucky63", "#stake-input_L63");
            this.put("select.fivefolds", "#multiple-type-acc5");
            this.put("stake.fivefolds", "#combis_5");
            this.put("select.superheinz", "#multiple-type-shnz");
            this.put("stake.superheinz", "#stake-input_SHNZ");
            this.put("select.sixfolds", "#multiple-type-acc6");
            this.put("stake.sixfolds", "#stake-input_ACC6");
            this.put("select.magnificent7", "#multiple-type-mag7");
            this.put("stake.magnificent7", "#stake-input_MAG7");
            this.put("select.goliath", "#multiple-type-gol");
            this.put("stake.goliath", "#stake-input_GOL");
            this.put("select.sevenfolds", "#multiple-type-acc7");
            this.put("stake.sevenfolds", "#stake-input_ACC7");
            this.put("select.eightfolds", "#multiple-type-acc8");
            this.put("stake.eightfolds", "#stake-input_ACC8");
            this.put("select.ninefolds", "#multiple-type-acc9");
            this.put("stake.ninefolds", "#stake-input_ACC9");
            this.put("select.accumulator", "#multiple-type-ac");
            this.put("stake.accumulator", "#stake-input_AC");
            this.put("select.straightforecast", "#cast-bet_0SF");
            this.put("stake.straightforecast", "#stake-input_0SF");
            this.put("div.single-bet", "div.single-bet");
            this.put("input.include-in-multiples", "input.selection-check");
        }
    }

    // Virtual Sports Selector Variables:

    public static final String MARKET_TITLE = "//h2[contains(.,'Markets')]";
    public static final String VIRTUAL_FOOTBALL_TITLE = "//h2[contains(.,'Virtual Football')]";
    public static final String VIRTUAL_SECTION_TITLE = "//header[contains(@class,'header')]/h2[contains(.,'%s')]";
    public static final String VIRTUAL_EVENT_TITLE = "//header/h2[contains(.,'%s')]";
    public static final String NAVIGATION_ARROW_NEXT = ".next-event.header-event-controls";
    public static final String NAVIGATION_ARROW_PREVIOUS = ".previous-event.header-event-controls";
    public static final HashMap<String, String> bsSelectors = new BSSelectorHashMap();
    public static final String CAROUSEL_OPTION = "//p[contains(.,'%s')]";
    public static final String FEATURED_OPTION_SELECTED = "li[class*='active'] i[class*='highlights']";
    public static final String FOOTBALL_MATCH_BETTING_SECTION = "a[title*='Match Betting']";
    public static final String FOOTBALL_CORRECT_SCORE_SECTION = "a[title*='Correct Score']";
    public static final String FOOTBALL_TOTAL_GOALS_SECTION = "a[title*='Total Goals']";
    public static final String FOOTBALL_TOTAL_GOALS_UNDER_OVER_SECTION = "a[title*='Under']";
    public static final String FOOTBALL_ASIAN_HANDICAP_SECTION = "a[title*='Asian Handicap']";
    public static final String FOOTBALL_DOUBLE_CHANCE_SECTION = "a[title*='Double Chance']";
    public static final String FOOTBALL_FIRST_SELECTION = "(//button[contains(@id,'OB_OU')])[1]";
    public static final String FOOTBALL_SPECIFIC_SELECTION = "(//button[contains(@id,'OB_OU')])[%s]";
    public static final String FOOTBALL_SELECTION = "section[id^='OB_EV']";
    public static final String PAGE_SELECTIONS = "button[id^='OB_OU']";
    public static final String PAGE_MEETINGS = ".button-standard.race-meeting__selection";
    public static final String SECTION_BOTTOM_BANNER = "#virtual-rules-banner";
    public static final String SECTION_VIDEO_PLAYER = ".stream-player.ng-scope";
    public static final String CAROUSEL_SECTION_FIRST_SELECTION = "(//button[contains(@class,'selection')])[1]";


    // Bet format Var - Format it with the order number of bet

    public static final String BET_TIME = "(//time[@data-type='event-starttime'])[%s]";
    public static final String BET_TITLE = "(//h3[contains(@class,'list-item--event')])[%s]";
    public static final String BET_LINK_TO_EVENT = "(//a[contains(@class,'list-item--link')])[%s]";
    public static final String BET_BUTTON = "(//button[contains(@id,'OB_OU')])[%s]";

    // Bet format Var - These will return a list of elements

    public static final String BET_TIME_LIST = "time[data-type='event-starttime']";
    public static final String BET_TITLE_LIST = "h3[class*='list-item--event']";
    public static final String BET_LINK_TO_EVENT_LIST = "a[class*='list-item--link']";
    public static final String BET_BUTTON_LIST = "button[id^='OB_OU']";

    // Top Bet Variables

    public static final String FIRST_TOP_BET_BUTTON_FROM_WIDGET = "(//li[@class='topbets__list-item']//button)[1]";
    public static final String TOP_BET_BUTTON_BY_EVENT_ID = ".topbets.clickable-selections button[data-event*='%s']";
    public static final String TOP_BET_BUTTON_BY_MARKET_ID = "button[data-market*='%s']";
    public static final String TOP_BET_TIME_BY_EVENT_ID = "//li[contains(@id,'%s')]//time";
    public static final String TOP_BET_TITLE_BY_EVENT_ID = "//li[contains(@id,'%s')]//h3";
    public static final String TOP_BET_LINK_BY_EVENT_ID = "//li[contains(@id,'%s')]//a";
    public static final String SHOW_MORE_TOP_BETS_LINK = ".-margin-top.topbets__footer.-text-center a";
    public static final String ALL_TOP_BETS_DISPLAYED = ".topbets.clickable-selections .topbets__list-item--link";

    // Mobile Footer Variables


    // Generic Variables

    // Note: this one will return all the Active "clickeable" selections from the current page - Returns Multiple Webelements
    public static final String ACTIVE_SELECTIONS = "//div[contains(@id, 'OB_MA') and not(contains(@class, 'disabled-market')) and not(contains(@class, 'displayNone'))]//button[contains(@id, 'OB_OU') and not(contains(@class,'disabled'))]";




    // Footer Selector Variables
    public static final String A_TO_Z_SPORTS_ELEMENTS_ON_FOOTER = "//section[@class='localnavigation__column-dropdown']/a[contains(@href,'/betting/en-gb/')]";

///////// datat from the page selectors

    public static final String MARKET_LINK = ".btmarket__link-name";

    public static final String IN_PLAY_ACTIVE_SELECTION =
            "[id^='OB_EV'][data-betinrun='true']:not(.disabled-event) " +
                    "[id^='OB_MA'][data-betinrun='true'][data-status='A']:not(.disabled-market) " +
                    "[id^='OB_OU'][data-status='A']:not(.disabled)";

    public static final String IN_PLAY_ACTIVE_EVENT =
            "[id^='OB_EV'][data-betinrun='true']:not(.disabled-event)";

    public static final String IN_PLAY_SUSPENDED_SELECTION =
            "[id^='OB_EV'][data-betinrun='true'].disabled-event [id^='OB_OU'], " +
                    "[id^='OB_MA'][data-betinrun='true'].disabled-market [id^='OB_OU'], " +
                    "[id^='OB_EV'][data-betinrun='true'] [id^='OB_OU'].disabled, " +
                    "[id^='OB_MA'][data-betinrun='true'] [id^='OB_OU'].disabled";

    public static final String PRE_MATCH_ACTIVE_SELECTION =
            "[id^='OB_EV'][data-betinrun='false']:not(.disabled-event) " +
                    "[id^='OB_MA'][data-betinrun='false'][data-status='A']:not(.disabled-market) " +
                    "[id^='OB_OU'][data-status='A']:not(.disabled)";

    public static final String PRE_MATCH_ACTIVE_EVENT =
            "[id^='OB_EV'][data-betinrun='false']:not(.disabled-event)";

    public static final String PRE_MATCH_SUSPENDED_SELECTION =
            "[id^='OB_EV'][data-betinrun='false'].disabled-event [id^='OB_OU'], " +
                    "[id^='OB_MA'][data-betinrun='false'].disabled-market [id^='OB_OU'], " +
                    "[id^='OB_EV'][data-betinrun='false'] [id^='OB_OU'].disabled, " +
                    "[id^='OB_MA'][data-betinrun='false'] [id^='OB_OU'].disabled";

    public static final String ACTIVE_SELECTION =
            "[id^='OB_EV']:not(.disabled-event) " +
                    "[id^='OB_MA'][data-status='A']:not(.disabled-market) " +
                    "[id^='OB_OU'][data-status='A']:not(.disabled)";

    public static final String SUSPENDED_SELECTION =
            ".disabled-event [id^='OB_OU'], " +
                    ".disabled-market [id^='OB_OU'], " +
                    "[id^='OB_OU'].disabled";

    public static final String ACTIVE_EVENT = ACTIVE_SELECTION;

    public static final String IN_PLAY_EVENT =
            "[id^='OB_EV'][data-betinrun='true']";

    public static final String IN_PLAY_EVENT_LINK =
            "[id^='OB_EV'][data-betinrun='true'] " + MARKET_LINK;

    public static final String PRE_MATCH_EVENT =
            "[id^='OB_EV'][data-betinrun='false']";

    public static final String PRE_MATCH_EVENT_LINK =
            "[id^='OB_EV'][data-betinrun='false'] " + MARKET_LINK;

    public static final String SELECTION_BUTTON_DETAILS = ".betbutton";

    public static String ACTIVE_SELECTION_FROM_EVENT_ID(String eventId) {
        return String.format(
                "[id='%s']:not(.disabled-event) " +
                        "[id^='OB_MA'][data-status='A']:not(.disabled-market) " +
                        "[id^='OB_OU'][data-status='A']:not(.disabled)",
                eventId);
    }

    public static String EVENT_LINK_FROM_EVENT_ID(String eventId) {
        return String.format(
                "[id='%s'] " + MARKET_LINK, eventId);
    }

    public static String IS_SELECTION_SUSPENDED(String selectionId) {
        return String.format(
                ".disabled-event [id^='OB_OU'], " +
                        ".disabled-market [id^='OB_OU'], " +
                        "[id^='OB_OU'].disabled",
                selectionId);
    }

    public static final String IN_PLAY_ACTIVE_MARKET =
            "[id^='OB_EV'][data-betinrun='true']:not(.disabled-event) " +
                    "[id^='OB_MA'][data-betinrun='true']:not(.disabled-market)";

    public static final String PRE_MATCH_ACTIVE_MARKET =
            "[id^='OB_EV'][data-betinrun='false']:not(.disabled-event) " +
                    "[id^='OB_MA'][data-betinrun='false']:not(.disabled-market)";

    public static final String ALL_SELECTIONS_DISPLAYED_ON_PAGE = "[id^='OB_MA']:not(.displayNone) .betbutton__odds";

    public final class Featured {
        public static final String SECTION_IN_PLAY = "#in-play-now";
        public static final String SECTION_HIGHLIGHTS = "#match-highlights";
    }

}