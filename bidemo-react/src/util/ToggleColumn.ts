const toggleColumn = (
    index: number,
    viewedColumns: number[],
    setViewedColumns: React.Dispatch<React.SetStateAction<number[]>>,
    currentColumns: any[],
    setCurrentColumns: React.Dispatch<React.SetStateAction<any[]>>
) => {
    if (viewedColumns.includes(index)) {
        setViewedColumns(viewedColumns.filter((colIndex) => colIndex !== index));
        setCurrentColumns((prevColumns) => {
            const newColumns = [...prevColumns];
            const currentColumn = newColumns[index];
            if (currentColumn.children) {
                const removeChildren = (parentIndex: number) => {
                    if (newColumns[parentIndex]?.children) {
                        newColumns[parentIndex]?.children?.forEach((child: any, i: number) => {
                            const childIndex = parentIndex + (newColumns[parentIndex]?.children?.length || 0) - i;
                            removeChildren(childIndex);
                            newColumns.splice(childIndex, 1);
                        });
                    }
                };
                removeChildren(index);
            }
            return newColumns;
        });
    } else {
        setViewedColumns([...viewedColumns, index]);
        if (currentColumns[index]?.children) {
            setCurrentColumns((prevColumns) => {
                const newColumns = [...prevColumns];
                newColumns.splice(index + 1, 0, ...(prevColumns[index]?.children || []));
                return newColumns;
            });
        }
    }
};

export default toggleColumn;