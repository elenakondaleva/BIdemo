import React, { useState, useEffect } from "react";
import {DimensionHierarchy, TableProps} from "../model/Dimension";

const Table: React.FC<TableProps> = ({ data, columns, rows }) => {
    const [currentColumns, setCurrentColumns] = useState(columns);
    const [viewedColumns, setViewedColumns] = useState<number[]>([]);
    const [currentRows, setCurrentRows] = useState(rows);
    const [viewedRows, setViewedRows] = useState<number[]>([]);
    useEffect(() => {
        setCurrentColumns(columns || []);
    }, [columns]);

    useEffect(() => {
        setCurrentRows(rows || []);
    }, [rows]);

    const renderColumns = () => {
        return currentColumns.map((column: any, index: number) => (
            <React.Fragment key={index}>
                <th key={index}>
                    {column.dimensionName}{" "}
                    {column.children && (
                        <button onClick={() => toggleColumn(index)}>
                            {viewedColumns.includes(index) ? "<" : ">"}
                        </button>
                    )}
                </th>
            </React.Fragment>
        ));
    };

    const renderRows = () => {
        return currentRows.map((row: any, index: number) => (
            <React.Fragment key={index}>
                <tr>
                    <td>
                        <div
                            className="expander"
                            style={{
                                paddingLeft: `${row.level * 2}rem`
                            }}
                        >
                            <>
                                {row.dimensionName}{" "}
                                {row.children && (
                                    <button onClick={() => toggleRow(index)}>
                                        {viewedRows.includes(index) ? (
                                            <svg
                                                xmlns="http://www.w3.org/2000/svg"
                                                fill="none"
                                                width="16"
                                                viewBox="0 0 24 24"
                                                strokeWidth="1.5"
                                                stroke="#33b4eb"
                                            >
                                                <path
                                                    strokeLinecap="round"
                                                    strokeLinejoin="round"
                                                    d="M19.5 8.25l-7.5 7.5-7.5-7.5"
                                                />
                                            </svg>
                                        ) : (
                                            <svg
                                                xmlns="http://www.w3.org/2000/svg"
                                                fill="none"
                                                width="16"
                                                viewBox="0 0 24 24"
                                                strokeWidth="1.5"
                                                stroke="#33b4eb"
                                            >
                                                <path
                                                    strokeLinecap="round"
                                                    strokeLinejoin="round"
                                                    d="M8.25 4.5l7.5 7.5-7.5 7.5"
                                                />
                                            </svg>
                                        )}
                                    </button>
                                )}
                            </>
                        </div>
                    </td>
                    {renderDataRows(row)}
                </tr>
            </React.Fragment>
        ));
    };

    const renderDataRows = (row: any[]) => {
        return currentColumns.map((col: DimensionHierarchy, index: number) => (
            <React.Fragment key={index}>
                <td>{getCellValue(col, row)}</td>
            </React.Fragment>
        ));
    };

    const toggleColumn = (index: number) => {
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

    const toggleRow = (index: number) => {
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

    const getCellValue = (col: any, row: any) => {
        if (!data || !Array.isArray(data)) {
            console.error("'data' is undefined or not an array.");
            return "-";
        }
        const foundItems =
            data.filter(item => item.dimensionOneId === col.dimensionId && item.dimensionTwoId === row.dimensionId);
        if (foundItems.length > 0) {
            return foundItems[0].rawData;
        } else {
            return "-";
        }
    };

    return (
        <table>
            <thead>
            <tr>
                <th></th>
                {renderColumns()}
            </tr>
            </thead>
            <tbody>
            {renderRows()}
            </tbody>
        </table>
    );
};

export default Table;

