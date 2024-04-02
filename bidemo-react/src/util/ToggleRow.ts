const toggleRow = (
    index: number,
    viewedRows: number[],
    setViewedRows: React.Dispatch<React.SetStateAction<number[]>>,
    currentRows: any[],
    setCurrentRows: React.Dispatch<React.SetStateAction<any[]>>
) => {
    if (viewedRows.includes(index)) {
        setViewedRows(viewedRows.filter((rowIndex) => rowIndex !== index));
        setCurrentRows((prevRows) => {
            const newRows = [...prevRows];
            const currentRow = newRows[index];
            if (currentRow.children) {
                const removeChildren = (parentIndex: number) => {
                    if (newRows[parentIndex]?.children) {
                        newRows[parentIndex].children?.forEach((child: any, i: number) => {
                            const childIndex = parentIndex + (newRows[parentIndex]?.children?.length || 0) - i;
                            removeChildren(childIndex);
                            newRows.splice(childIndex, 1);
                        });
                    }
                };
                removeChildren(index);
            }
            return newRows;
        });
    } else {
        setViewedRows([...viewedRows, index]);
        if (currentRows[index]?.children) {
            setCurrentRows((prevRows) => {
                const newRows = [...prevRows];
                newRows.splice(index + 1, 0, ...(prevRows[index]?.children || []));
                return newRows;
            });
        }
    }
};

export default toggleRow;