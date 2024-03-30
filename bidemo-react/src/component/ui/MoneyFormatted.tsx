import {formatCurrency} from "../../util/NumberUtils";

interface MoneyFormattedProps {
    amount: number;
}

const MoneyFormatted = ({ amount }: MoneyFormattedProps) => {
    return (
        <span>
            {formatCurrency(amount)}
        </span>
    );
};

export default MoneyFormatted;
