import React, {useState, useEffect} from "react";
import {DimensionHierarchy, TableProps} from "../model/Types";
import MoneyFormatted from "./ui/MoneyFormatted";
import {ExpandIcon} from "../icons/ExpandIcon";
import {CollapseIcon} from "../icons/CollapseIcon";
import {CollapseHorizontIcon} from "../icons/CollapseHorizont";
import toggleColumn from "../util/ToggleColumn";
import toggleRow from "../util/ToggleRow";

const DataCubeTable: React.FC<TableProps> = ({ data, columns, rows }) => {
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

    const handleToggleColumn = (index: number) => {
        toggleColumn(
            index,
            viewedColumns,
            setViewedColumns,
            currentColumns,
            setCurrentColumns
        );
    };

    const handleToggleRow = (dimensionId: number) => {
        toggleRow(
            dimensionId,
            viewedRows,
            setViewedRows,
            currentRows,
            setCurrentRows
        );
    };

    const renderColumns = () => {
        return currentColumns.map((column: any, index: number) => {
            const {dimensionId} = column;
            return (
                <th key={dimensionId}>
                    <div
                        className="expander"
                        style={{
                            display: "flex", alignItems: "normal"
                        }}
                    >
                        {column.dimensionName}{" "}
                        {column.children && (
                            <button style={{paddingLeft: 5}} onClick={() => handleToggleColumn(index)}>
                                {viewedColumns.includes(index) ? <CollapseHorizontIcon/> : <CollapseIcon/>}
                            </button>
                        )}
                    </div>
                </th>
            );
        });
    };

    const renderRows = () => {
        return currentRows.map((row: any, index: number) => {
            const {dimensionId} = row;
            return (
            <tr key={dimensionId}>
                <td>
                    <div
                        className="expander"
                        style={{
                            display: "flex", alignItems: "normal", paddingLeft: `${row.level * 2}rem`
                        }}
                    >
                        <>
                            {row.dimensionName}{" "}
                            {row.children && (
                                <button style={{paddingLeft: 5}} onClick={() => handleToggleRow(index)}>
                                    {viewedRows.includes(index) ? (<ExpandIcon/>) : (<CollapseIcon/>)}
                                </button>
                            )}
                        </>
                    </div>
                </td>
                {renderDataRows(row)}
            </tr>
            );
        });
    };

    const renderDataRows = (row: any[]) => {
        return currentColumns.map((col: DimensionHierarchy, index: number) => {
            const {dimensionId} = col;
            return (
                <td key={dimensionId}>
                    {getCellValue(col, row)}
                </td>
            );
        });
    };

    const getCellValue = (col: any, row: any) => {
        if (!data || !Array.isArray(data)) {
            console.error("'data' is undefined or not an array.");
            return "-";
        }
        const foundItems =
            data.filter(item => item.dimensionOneId === col.dimensionId && item.dimensionTwoId === row.dimensionId);
        if (foundItems.length > 0) {
            return <div className="text-right">
                <MoneyFormatted amount={foundItems[0].rawData} />
            </div>
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

export default DataCubeTable;

