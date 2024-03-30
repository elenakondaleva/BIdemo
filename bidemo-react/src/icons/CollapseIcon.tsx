import React from "react";

interface CollapseIconAttributes {
    className?: string,
}

export const CollapseIcon: React.FC<CollapseIconAttributes> = ({ className = '' }: CollapseIconAttributes) => {
    return (
    <svg xmlns="http://www.w3.org/2000/svg" className="icon" fill="none" width="16" viewBox="0 0 24 24">
        <path fill="currentColor" d="M8.25 4.5l7.5 7.5-7.5 7.5"/>
    </svg>
    );
}