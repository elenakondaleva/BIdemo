import React from "react";

interface CollapseHorizontIconAttributes {
    className?: string,
}

export const CollapseHorizontIcon: React.FC<CollapseHorizontIconAttributes> = ({ className = '' }: CollapseHorizontIconAttributes) => {
    return (
        <svg xmlns="http://www.w3.org/2000/svg" className="icon" fill="none"  width="16" height="16" viewBox="0 0 52 52">
            <path fill="currentColor" d="M38 8.3v35.4c0 1-1.3 1.7-2.2.9L14.6 27.3c-.8-.6-.8-1.9 0-2.5L35.8 7.3c.9-.7 2.2-.1 2.2 1"/>
        </svg>
    );
}